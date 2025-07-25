package com.nihal.threadlearn.volatilevsatomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicExample {
    public static void main(String[] args) throws InterruptedException{
        
        // Counter counter = new Counter();
        AtomicCounter counter = new AtomicCounter();
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(counter.getCounter());

    }
}

class Counter {

    private volatile int counter = 0; 
    // the problem with this volatile var is more than one threads can modify this var at same time. 
    // previously we were just reading a boolean state not modifying simultaneously like this example

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}

class AtomicCounter {
    
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}