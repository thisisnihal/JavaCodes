package dsa;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class CrashCourse1 {
    static void solve() throws Exception {

        /*
         * -------------------------------------------------------
         * 1. unordered set
         * -------------------------------------------------------
         */
        Set<Integer> hs = new HashSet<>();

        // Insert
        hs.add(10);
        hs.add(20);
        hs.add(10); // duplicates ignored

        // Check existence
        System.out.println(hs.contains(20)); // true

        // Remove
        hs.remove(10);

        // Size
        System.out.println(hs.size());

        // Iterate
        for (int x : hs)
            System.out.println(x);

        /*
         * -------------------------------------------------------
         * 2. ordered set
         * -------------------------------------------------------
         */

        Set<Integer> ts = new TreeSet<>(); // Sorted ascending
        ts.add(30);
        ts.add(10);
        ts.add(20);
        System.out.println(ts); // [10, 20, 30]

        Set<Integer> lhs = new LinkedHashSet<>(); // Keeps insertion order
        lhs.add(3);
        lhs.add(1);
        lhs.add(2);
        System.out.println(lhs); // [3, 1, 2]

        /*
         * -------------------------------------------------------
         * 3. unordered map
         * -------------------------------------------------------
         */
        Map<String, Integer> hm = new HashMap<>();

        // Insert
        hm.put("Alice", 25);
        hm.put("Bob", 30);

        // Update
        hm.put("Alice", 26);

        // Get value
        System.out.println(hm.get("Alice")); // 26
        System.out.println(hm.getOrDefault("Eve", -1)); // -1

        // Check key
        System.out.println(hm.containsKey("Bob")); // true

        // Remove
        hm.remove("Alice");

        // Iterate
        for (Map.Entry<String, Integer> e : hm.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }

        /*
         * -------------------------------------------------------
         * 4. ordered map
         * -------------------------------------------------------
         */
        Map<Integer, String> tm = new TreeMap<>();
        tm.put(2, "B");
        tm.put(1, "A");
        tm.put(3, "C");
        System.out.println(tm); // {1=A, 2=B, 3=C}

        Map<Integer, String> lhm = new LinkedHashMap<>();
        lhm.put(2, "B");
        lhm.put(1, "A");
        System.out.println(lhm); // {2=B, 1=A}

        /*
         * -------------------------------------------------------
         * Stack using deque
         * -------------------------------------------------------
         */
        Deque<Integer> stack = new ArrayDeque<>();

        // Push
        stack.push(10);
        stack.push(20);

        // Peek
        System.out.println(stack.peek()); // 20

        // Pop
        System.out.println(stack.pop()); // 20

        // Empty check
        System.out.println(stack.isEmpty()); // false

        /*
         * -------------------------------------------------------
         * Normal Queue (FIFO)
         * -------------------------------------------------------
         */
        Queue<Integer> q = new LinkedList<>();

        q.add(10); // insert
        q.add(20);

        System.out.println(q.peek()); // front element (10)
        System.out.println(q.poll()); // remove and return front (10)
        System.out.println(q.isEmpty());

        /*
         * -------------------------------------------------------
         * Priority Queue (min-heap by default)
         * -------------------------------------------------------
         */

        // Min-Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(30);
        pq.add(10);
        pq.add(20);

        System.out.println(pq.peek()); // 10 (smallest)
        System.out.println(pq.poll()); // removes 10

        // Max-heap
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        maxPQ.add(30);
        maxPQ.add(10);
        System.out.println(maxPQ.poll()); // 30

        /*
         * -------------------------------------------------------
         * Deque - double ended queue
         * -------------------------------------------------------
         */
        Deque<Integer> dq = new ArrayDeque<>();

        dq.addFirst(1); // [1]
        dq.addLast(2); // [1,2]
        dq.addFirst(0); // [0,1,2]

        System.out.println(dq.peekFirst()); // 0
        System.out.println(dq.pollFirst()); // remove and return 0 
        
        System.out.println(dq.peekLast()); // 2
        System.out.println(dq.pollLast()); //  remove and return 2

        

    }

    /*
     * -------------------------------------------------------
     * Boilerplate
     * -------------------------------------------------------
     */
    static int scanInt() throws IOException {
        return parseInt(scanString());
    }

    static long scanLong() throws IOException {
        return parseLong(scanString());
    }

    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
}
