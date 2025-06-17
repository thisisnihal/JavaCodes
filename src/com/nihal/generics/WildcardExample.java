package com.nihal.generics;

    // using wildcard you can pass only a certain data type
    public class WildcardExample<T extends Number> {

        private Object data[];
        private static int DEFAULT_SIZE = 10;
        private int size = 0;
    
        public WildcardExample() {
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
