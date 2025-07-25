package dsa.linkedlists;

import java.util.HashMap;

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head, tail; // dummy head and tail for easy list manipulation

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0, 0); // dummy head
        tail = new Node(0, 0); // dummy tail

        // head <-> tail
        head.next = tail;
        tail.prev = head;
    }

    // Remove a node from list
    // Head <-> A <-> B <-> C <-> D <-> tail supose we have to remove B
    private void remove(Node node) {
        node.prev.next = node.next; // A -> C
        node.next.prev = node.prev; // A <- C
    }

    // Insert a node at front (right after head).. we want this:   Head <-> NewNode <-> A <-> B <-> tail
    private void insertAtFront(Node node) {
        // intially... Head -> A -> B
        node.next = head.next;  // NewNode -> A      (1)
        node.prev = head;       // Head <- NewNode   (2)
        // Head <- NewNode -> A

        head.next.prev = node; // NewNode <- A       (1')
        head.next = node;      // Head -> NewNode    (2')

        //by observe 1 and 2, we get: Head <-> NewNode <-> A <-> B <-> tail
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        remove(node);
        insertAtFront(node); // move to front
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            insertAtFront(node);
        } else {
            if (map.size() == capacity) {
                // Evict LRU from back (before dummy tail)
                Node lru = tail.prev;
                remove(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            insertAtFront(newNode);
            map.put(key, newNode);
        }
    }

    // Optional: for debugging
    public void printCache() {
        Node curr = head.next;
        System.out.print("Cache: ");
        while (curr != tail) {
            System.out.print("[" + curr.key + ":" + curr.value + "] ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        cache.printCache(); // [3:30] [2:20] [1:10]

        System.out.println(cache.get(2)); // 20
        cache.printCache(); // [2:20] [3:30] [1:10]

        cache.put(4, 40); // evict 1
        cache.printCache(); // [4:40] [2:20] [3:30]

        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 30
        cache.printCache(); // [3:30] [4:40] [2:20]
    }
}
