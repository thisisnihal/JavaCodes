Here’s a tiny-but-solid HTTP/1.1 server built **from scratch in pure Java** (single file). It serves static files from a chosen directory, returns proper status codes, adds essential headers, blocks `..` path traversal, and handles multiple clients via a thread pool.

Copy this into `TinyHttpServer.java`:

```java
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;

public class TinyHttpServer {
    private final int port;
    private final Path docRoot;
    private final ServerSocket serverSocket;
    private final ExecutorService pool;
    private volatile boolean running = true;

    public TinyHttpServer(int port, Path docRoot, int threads) throws IOException {
        this.port = port;
        this.docRoot = docRoot.toRealPath();
        this.serverSocket = new ServerSocket();
        this.serverSocket.setReuseAddress(true);
        this.serverSocket.bind(new InetSocketAddress(port));
        this.pool = Executors.newFixedThreadPool(threads);
    }

    public void start() {
        System.out.println("TinyHttpServer listening on http://localhost:" + port + " serving " + docRoot);
        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        while (running) {
            try {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(10_000);
                pool.submit(() -> handleClient(socket));
            } catch (SocketException se) {
                // likely shutdown
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        running = false;
        try { serverSocket.close(); } catch (IOException ignored) {}
        pool.shutdownNow();
        System.out.println("TinyHttpServer stopped.");
    }

    private static final Pattern MULTISPACE = Pattern.compile("\\s+");

    private void handleClient(Socket socket) {
        try (socket;
             BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
             OutputStream out = socket.getOutputStream()) {

            // Parse request line
            String requestLine = readLine(in);
            if (requestLine == null || requestLine.isEmpty()) return;

            String[] parts = MULTISPACE.split(requestLine, 3);
            if (parts.length < 3) { send400(out, "Bad request line"); return; }

            String method = parts[0];
            String rawTarget = parts[1];
            String httpVersion = parts[2];

            // Only HTTP/1.0 and 1.1
            if (!httpVersion.startsWith("HTTP/1.")) { send505(out); return; }

            // Headers
            Map<String,String> headers = new LinkedHashMap<>();
            String line;
            while ((line = readLine(in)) != null && !line.isEmpty()) {
                int idx = line.indexOf(':');
                if (idx > 0) {
                    String name = line.substring(0, idx).trim();
                    String value = line.substring(idx + 1).trim();
                    headers.put(name.toLowerCase(Locale.ROOT), value);
                }
            }

            // We only support GET and HEAD here
            if (!method.equals("GET") && !method.equals("HEAD")) {
                send405(out);
                return;
            }

            // Strip query and fragment
            String pathPart = rawTarget;
            int q = pathPart.indexOf('?');
            if (q >= 0) pathPart = pathPart.substring(0, q);
            int hash = pathPart.indexOf('#');
            if (hash >= 0) pathPart = pathPart.substring(0, hash);

            // Normalize and prevent traversal
            pathPart = URLDecoder.decode(pathPart, StandardCharsets.UTF_8);
            if (pathPart.isEmpty() || pathPart.equals("/")) pathPart = "/index.html";
            Path requested = docRoot.resolve(pathPart.substring(1)).normalize();

            if (!requested.startsWith(docRoot)) {
                send403(out);
                return;
            }

            if (Files.isDirectory(requested)) requested = requested.resolve("index.html");

            if (!Files.exists(requested) || !Files.isReadable(requested) || Files.isDirectory(requested)) {
                send404(out, pathPart);
                return;
            }

            long length = Files.size(requested);
            String mime = guessMime(requested);
            byte[] headerBytes = buildHeaders(200, "OK", mime, length, null);

            out.write(headerBytes);
            if (!method.equals("HEAD")) {
                try (InputStream fileIn = Files.newInputStream(requested)) {
                    fileIn.transferTo(out);
                }
            }
            out.flush();

        } catch (SocketTimeoutException e) {
            // ignore idle client
        } catch (IOException e) {
            // best-effort 500 if still possible
            try { send500(socket.getOutputStream(), e.getMessage()); } catch (Exception ignored) {}
        }
    }

    private static String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream(128);
        int prev = -1, curr;
        while ((curr = in.read()) != -1) {
            if (prev == '\r' && curr == '\n') break;
            if (prev == '\r' && curr != '\n') buf.write('\r'); // lone CR
            if (curr != '\n') buf.write(curr);
            prev = curr;
            if (buf.size() > 8192) throw new IOException("Header line too long");
        }
        if (curr == -1 && buf.size() == 0) return null;
        return buf.toString(StandardCharsets.UTF_8);
    }

    private static byte[] buildHeaders(int code, String reason, String contentType, long contentLength, String extra) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ").append(code).append(' ').append(reason).append("\r\n");
        sb.append("Date: ").append(httpDate()).append("\r\n");
        sb.append("Server: TinyHttpServer/1.0\r\n");
        if (contentType != null) sb.append("Content-Type: ").append(contentType).append("\r\n");
        if (contentLength >= 0) sb.append("Content-Length: ").append(contentLength).append("\r\n");
        sb.append("Connection: close\r\n");
        if (extra != null && !extra.isEmpty()) sb.append(extra);
        sb.append("\r\n");
        return sb.toString().getBytes(StandardCharsets.US_ASCII);
    }

    private static void sendText(OutputStream out, int code, String reason, String body) throws IOException {
        byte[] payload = body.getBytes(StandardCharsets.UTF_8);
        out.write(buildHeaders(code, reason, "text/plain; charset=utf-8", payload.length, null));
        out.write(payload);
        out.flush();
    }

    private static void send400(OutputStream out, String msg) throws IOException { sendText(out, 400, "Bad Request", "400 Bad Request\n" + msg); }
    private static void send403(OutputStream out) throws IOException { sendText(out, 403, "Forbidden", "403 Forbidden"); }
    private static void send404(OutputStream out, String path) throws IOException { sendText(out, 404, "Not Found", "404 Not Found: " + path); }
    private static void send405(OutputStream out) throws IOException {
        byte[] hdr = buildHeaders(405, "Method Not Allowed", "text/plain; charset=utf-8", 18, "Allow: GET, HEAD\r\n");
        out.write(hdr);
        out.write("Method Not Allowed".getBytes(StandardCharsets.UTF_8));
        out.flush();
    }
    private static void send500(OutputStream out, String msg) throws IOException { sendText(out, 500, "Internal Server Error", "500 Internal Server Error\n" + msg); }
    private static void send505(OutputStream out) throws IOException { sendText(out, 505, "HTTP Version Not Supported", "505 HTTP Version Not Supported"); }

    private static String httpDate() {
        SimpleDateFormat fmt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        fmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        return fmt.format(new Date());
    }

    private static String guessMime(Path p) {
        String name = p.getFileName().toString().toLowerCase(Locale.ROOT);
        if (name.endsWith(".html") || name.endsWith(".htm")) return "text/html; charset=utf-8";
        if (name.endsWith(".css")) return "text/css; charset=utf-8";
        if (name.endsWith(".js")) return "application/javascript; charset=utf-8";
        if (name.endsWith(".json")) return "application/json; charset=utf-8";
        if (name.endsWith(".png")) return "image/png";
        if (name.endsWith(".jpg") || name.endsWith(".jpeg")) return "image/jpeg";
        if (name.endsWith(".gif")) return "image/gif";
        if (name.endsWith(".svg")) return "image/svg+xml";
        if (name.endsWith(".txt")) return "text/plain; charset=utf-8";
        try {
            String probe = Files.probeContentType(p);
            if (probe != null) return probe;
        } catch (IOException ignored) {}
        return "application/octet-stream";
    }

    public static void main(String[] args) throws Exception {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
        Path root = Paths.get(args.length > 1 ? args[1] : ".").toAbsolutePath();
        int threads = args.length > 2 ? Integer.parseInt(args[2]) : Math.max(4, Runtime.getRuntime().availableProcessors());
        TinyHttpServer server = new TinyHttpServer(port, root, threads);
        server.start();
    }
}
```

### How to run

```bash
# 1) Save the file above as TinyHttpServer.java
# 2) Compile
javac TinyHttpServer.java

# 3) Create a simple site
mkdir www
printf '<h1>Hello, world</h1>' > www/index.html

# 4) Run (port 8080, serving ./www)
java TinyHttpServer 8080 ./www
```

Open `http://localhost:8080/` and you’ll see `index.html`. Drop more files into `www` and they’ll be served.

### What this server does (and why it’s “real enough”)

* Accepts many clients concurrently (thread pool).
* Parses the request line and headers; responds with **HTTP/1.1** status line, `Date`, `Server`, `Content-Type`, `Content-Length`, and `Connection` headers.
* Correctly handles `GET` and `HEAD`; rejects others with **405**.
* Serves files with sensible MIME types.
* Returns **403** for attempted path traversal (e.g., `/../secret`).
* Returns **404** for missing files and **400/500/505** for bad requests/internal errors/version issues.
* Graceful shutdown via Ctrl-C (shutdown hook).

### Nice extensions (quick pointers)

* Directory listings: when `requested` is a directory, render an HTML index instead of forcing `index.html`.
* Keep-Alive: loop to handle multiple requests per connection when `Connection: keep-alive` (and default for HTTP/1.1). You’ll need to respect `Content-Length` precisely.
* Range requests: parse `Range` header and serve partial content (206).
* Logging: print common access log lines.
* TLS: wrap the socket with `SSLServerSocket` and configure a keystore.

