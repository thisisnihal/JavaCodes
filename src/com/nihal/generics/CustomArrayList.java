package com.nihal.generics;

public class CustomArrayList<T> {

    private Object data[];
    private static int DEFAULT_SIZE = 10;
    private int size = 0;

    public CustomArrayList() {
        this.data = new Object[DEFAULT_SIZE];
    }

    public void add(T num) {
        if (isFull()) {
            resize();
        }
        data[size++] = num;
    }

    private void resize() {
        Object temp[] = new Object[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isFull() {
        return size == data.length;
    }

    public T remove() {
        T removed = (T) (data[--size]);
        return removed;
    }

    public T get(int index) {
        if (index >= size) {
            System.out.print("Error : Index out of Range : ");
            return null;
        }
        return (T) (data[index]);
    }

    public int size() {
        return size;
    }

    public void set(int index, T value) {
        data[index] = value;
    }
}


 class CustomArrayMain {
    public static void main(String[] args) {
        // see official docs for more info.
        // ArrayList list = new ArrayList<>();
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(23);
        list.add(87);
        list.add(34);
        
        System.out.println(list.get(list.size() - 1));
        System.out.println("removed : " + list.remove());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println("size = " + list.size());

        System.out.println("\nCreating String Custom Array List\n");
        CustomArrayList<String> names = new CustomArrayList<>();
        names.add("Nihal");
        names.add("Jacobian");
        names.add("Tesla");
        System.out.println(names.get(names.size() - 1));
        System.out.println("removed : " + names.remove());
        System.out.println(names.get(0));
        System.out.println(names.get(1));
        System.out.println(names.get(2));
        System.out.println("size = " + names.size());
    }
 }




/*
 * public class CustomArrayList {
 * 
 * private int data[];
 * private static int DEFAULT_SIZE = 10;
 * private int size = 0;
 * 
 * public CustomArrayList() {
 * this.data = new int[DEFAULT_SIZE];
 * }
 * 
 * public void add(int num) {
 * if (isFull()) {
 * resize();
 * }
 * data[size++] = num;
 * }
 * 
 * private void resize() {
 * int temp[] = new int[data.length * 2];
 * 
 * for (int i = 0; i < data.length; i++) {
 * temp[i] = data[i];
 * }
 * data = temp;
 * }
 * 
 * private boolean isFull() {
 * return size == data.length;
 * }
 * public int remove () {
 * int removed = data[--size];
 * return removed;
 * }
 * public int get(int index) {
 * if (index >= size) {
 * System.err.print("Error : Index out of Range : ");
 * return -1;
 * }
 * return data[index];
 * }
 * public int size() {
 * return size;
 * }
 * public void set (int index, int value) {
 * data[index] = value;
 * }
 * }
 */