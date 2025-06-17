package com.nihal.threadlearn.sync;

public class Counter {
    private int count = 0;

    // adding synchronized keyword in method make this method accessed by one thread at a time.
    // no two threads can run this method simultaneously (you can remove synchronized keyword and try)
    // it also called critical section cause shared resouces is handled here
    public synchronized void increment() {
        count++;
    }


    // sometimes you dont want to make the compelete method synchronized 
    // in that case you can use synchronized block
    public  void increment1() {
        // ..some code that i dont want to make it synchronized
        synchronized(this) {    // use this keyword cause we want to make it sync for this object only
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}


// mututal exclution - prevents access to shared resources ..?
