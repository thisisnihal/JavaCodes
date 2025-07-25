package dsa.binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class TimeMapTest {
    public static void main(String[] args) {
        TimeMap tm = new TimeMap();
        tm.set("foo", "bar", 1);
        System.out.println(tm.get("foo", 1));
        System.out.println(tm.get("foo", 3));
        tm.set("foo", "bar2", 4);
        System.out.println(tm.get("foo", 4));
        System.out.println(tm.get("foo", 5));
    }
}

class TimeMap {

    class Pair implements Comparable<Pair> {
        int timestamp;
        String value;

        public Pair(int t, String v) {
            timestamp = t;
            value = v;
        }

        @Override
        public int compareTo(Pair other) {
            return Integer.compare(this.timestamp, other.timestamp);
        }
    }

    // key: {{timestamp, value}, {timestamp, value}, ...}
    private HashMap<String, List<Pair>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Pair(timestamp, value));
    }

    public String get(String key, int timestamp) {
        List<Pair> list = map.get(key);
        if (list == null)
            return "";
        int idx = Collections.binarySearch(list, new Pair(timestamp, ""));

        if (idx < 0) {
            int insertionPoint = -(idx + 1);
            int validIdx = insertionPoint - 1;
            if (validIdx >= 0)
                return list.get(validIdx).value;
            else
                return "";
        }
        return list.get(idx).value;
    }
}


//  what Collections.binarySearch(list, key) does:

// If an exact match is found, it returns the index of the element.

// If an exact match is not found, it returns -(insertion_point) - 1, where:

// insertion_point is the index at which the key would be inserted to maintain the sort order.

// This means the closest smaller timestamp would be at insertion_point - 1.

