package com.nihal.threadlearn.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

    // you can pass 'true' in argument to get fairness
    private final Lock lock = new ReentrantLock(true);

    public void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(1000); //simulate some work (may be in real world you call DB or run loops etc)
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock"); // always print first then do unlock
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        
        FairnessLockExample obj = new FairnessLockExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                obj.accessResource();
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

/*
case-1: OUTPUT when `private final Lock lock = new ReentrantLock();` fairness is false (default is false)
Thread 1 acquired the lock
Thread 2 acquired the lock
Thread 1 released the lock
Thread 2 released the lock
Thread 3 acquired the lock
Thread 3 released the lock

case-1: OUTPUT when `private final Lock lock = new ReentrantLock(true);` fairness is true
Thread 1 acquired the lock
Thread 1 released the lock
Thread 3 acquired the lock
Thread 3 released the lock
Thread 2 acquired the lock
Thread 2 released the lock

you can notice the order of execution doesnt matter here
 */