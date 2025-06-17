package com.nihal.functions;

import java.util.Arrays;

public class ChangeValue {
    public static void main(String[] args) {
        int a[] = { 1, 2, 3, 4, 5 };
        swapInt(1, 2, a);
        System.out.println(Arrays.toString(a));

        Integer b[] = { 1, 2, 3, 4, 5 };
        swapInteger(1, 2, b);
        System.out.println(Arrays.toString(b));

    }

    
    /**
     * @param i - index i : Integer
     * @param j  - index j : Integer
     * @param arr - array : Integer
     */
    private static void swapInteger(Integer i, Integer j, Integer arr[]) {

        // arr = Arrays.copyOf(arr, 5);  // uncomment it if you dont want to change the passed array in argument

        if (arr.length > 0 && i < arr.length && j < arr.length) {
            Integer temp = arr[i];
            arr[i] = arr[j];    // we are just modifying the object. so changes will also be reflected in orginal
            arr[j] = temp;
        } else {
            System.out.println("Index Out of Range. Swap cannot be made");
        }

    }

    private static void swapInt(int i, int j, int arr[]) {
        if (arr.length > 0 && i < arr.length && j < arr.length) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        } else {
            System.out.println("Index Out of Range. Swap cannot be made");
        }
    }
}
