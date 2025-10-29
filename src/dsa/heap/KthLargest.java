package dsa.heap;

import java.util.PriorityQueue;

public class KthLargest {

    private int k;
    PriorityQueue<Integer> minheap;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        minheap = new PriorityQueue<>(k+1);

        for (int i : nums) {
            add(i);
        }

    }

    public int add(int val) {

        minheap.add(val);
        if (minheap.size() > this.k) {
            minheap.poll();
        }
        return minheap.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 8, 2};
        int k = 3;
        KthLargest obj = new KthLargest(k, nums);
        int val = 3;
        int param_1 = obj.add(val);

        System.out.println(param_1);
    }
}

/*
a min-heap of size k always keeps the k largest elements seen so far.

The top of the heap (heap.peek()) is the kth largest element.

--- 
When the object is initialized:

You receive k and an initial list nums.

Insert all numbers from nums into the min-heap.

If the heap size exceeds k, remove the smallest one.

----
Each time you call add(val):

Add val to the heap.

If the heap size exceeds k, remove the smallest element (heap.poll()).

The kth largest is now the smallest in the heap (heap.peek()).

*/