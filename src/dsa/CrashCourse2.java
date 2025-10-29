package dsa;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class CrashCourse2 {
    static void solve() throws Exception {
        /*
         * -------------------------------------------------------
         * 1. Iterator with List
         * -------------------------------------------------------
         */
        List<String> list = new ArrayList<>(Arrays.asList("A","B","C"));
        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            String val = it.next();
            System.out.println(val);
            if (val.equals("B")) it.remove(); // safe removal
        }
        System.out.println(list); // [A, C]

        /*
         * -------------------------------------------------------
         * 2. Iterator with Set
         * -------------------------------------------------------
         */
        Set<Integer> set = new HashSet<>(Arrays.asList(10,20,30));
        Iterator<Integer> setIt = set.iterator();

        while (setIt.hasNext()) {
            int val = setIt.next();
            if (val == 20) setIt.remove();
        }
        System.out.println(set); // [10, 30]

        /*
         * -------------------------------------------------------
         * 3. Iterator with Map (keys, values, entries)
         * -------------------------------------------------------
         */
        Map<String,Integer> map = new HashMap<>();
        map.put("Alice", 25);
        map.put("Bob", 30);
        map.put("Charlie", 35);

        // Iterate over keys
        Iterator<String> keyIt = map.keySet().iterator();
        while (keyIt.hasNext()) {
            System.out.println("Key: " + keyIt.next());
        }

        // Iterate over values
        Iterator<Integer> valIt = map.values().iterator();
        while (valIt.hasNext()) {
            System.out.println("Value: " + valIt.next());
        }

        // Iterate over entries
        Iterator<Map.Entry<String,Integer>> entryIt = map.entrySet().iterator();
        while (entryIt.hasNext()) {
            Map.Entry<String,Integer> e = entryIt.next();
            if (e.getKey().equals("Bob")) entryIt.remove(); // safe removal
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println(map);

        /*
         * -------------------------------------------------------
         * 4. ListIterator (only for Lists, bidirectional)
         * -------------------------------------------------------
         */
        List<String> list2 = new ArrayList<>(Arrays.asList("X","Y","Z"));
        ListIterator<String> lit = list2.listIterator();

        // Forward traversal
        while (lit.hasNext()) {
            String val = lit.next();
            if (val.equals("Y")) {
                lit.set("YY"); // update
                lit.add("NEW"); // insert after YY
            }
        }
        System.out.println(list2); // [X, YY, NEW, Z]

        // Backward traversal
        while (lit.hasPrevious()) {
            System.out.println("Backward: " + lit.previous());
        }

        /*
         * -------------------------------------------------------
         * 5. forEachRemaining (Java 8+)
         * -------------------------------------------------------
         */
        Iterator<String> it2 = list2.iterator();
        it2.forEachRemaining(x -> System.out.println("forEachRemaining: " + x));

        /*
         * -------------------------------------------------------
         * 6. Enhanced for-loop (syntactic sugar for Iterator)
         * -------------------------------------------------------
         */
        for (String s : list2) {
            System.out.println("for-each: " + s);
        }
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
