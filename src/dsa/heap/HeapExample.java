package dsa.heap;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class HeapExample {

    public static void main(String[] args) {
        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        minheap.add(3);
        minheap.add(1);
        minheap.add(10);
        minheap.add(5);

        if (minheap.contains(10)) {
            System.out.println("10 is in the minheap!!");
        }

        // Retrieves, but does not remove, the head of this queue, or returns null if
        // this queue is empty
        System.out.println("minheap.peek(): " + minheap.peek());

        // Retrieves and removes the head of this queue, or returns null if this queue
        // is empty.
        System.out.println("minheap.poll(): " + minheap.poll());

        Iterator<Integer> itr = minheap.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());  // same as new PriorityQueue<>((a, b) -> b - a);

        class Task {
            int priority;
            String name;

            public Task(String name, int priority) {
                this.name = name;
                this.priority = priority;
            }
        }

        PriorityQueue<Task> taskQueue = new PriorityQueue<>(
                (a, b) -> Integer.compare(a.priority, b.priority));

        taskQueue.offer(new Task("Low", 3));
        taskQueue.offer(new Task("High", 1));
        taskQueue.offer(new Task("Medium", 2));

        System.out.println(taskQueue.poll().name); // "High"

    }

}



