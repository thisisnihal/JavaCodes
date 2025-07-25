package dsa.linkedlists;

public class CopyLL {
    Node head;
    Node tail;

    public CopyLL() {

    }

    public void insert(int val) {
        Node newNode = new Node(val);
        // newNode.random = random;
        
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    class Node {
        int value;
        Node next;
        Node random;

        public Node(int val) {
            this.value = val;
            this.next = null;
            this.random = null;
        }
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " -> ");
            temp = temp.next;
        }
        System.out.println("END");
    }

    public Node copyRandomList(Node head) {
        Node h = head;
        while (h != null) {
            Node temp = h;
            Node next = h.next;
            h.next = temp;
            h = h.next;
        }
        h = head;
        while (h != null) {
            if (h.next.random != null)
                h.next.random = h.random.next;
            h = h.next;
        }
        h = head.next;
        Node dummy = h;
        while (h.next != null) {
            h.next = h.next.next;
        }
        return dummy;
    }

    public static void main(String[] args) {

        CopyLL ll = new CopyLL();
        ll.insert(7);
        ll.insert(13);
        ll.insert(11);
        ll.insert(10);
        ll.insert(1);

        ll.display();
    }

}

// Definition for a Node.
