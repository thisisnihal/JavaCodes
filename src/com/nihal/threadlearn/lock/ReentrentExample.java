package com.nihal.threadlearn.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrentExample {
    private final Lock lock = new ReentrantLock();

    public void outer() {
        lock.lock(); // lockHoldCount = 1
        try {
            System.out.println("Outer method");
            inner();
        } finally {
            lock.unlock(); // lockHoldCount = 0
        }
    }

    public void inner() {
        lock.lock(); // it will lock again even though this thread was already locked. (lock count will increases i.e lockHoldCount = 2)
        try {
            System.out.println("Inner method");
        } finally {
            lock.unlock(); // lockHoldCount = 1
        }
    }

    public static void main(String[] args) {
        ReentrentExample re = new ReentrentExample();
        re.outer();
        // Runnable task = new Runnable() {
        //     @Override
        //     public void run() {
        //         re.outer();
        //     }
        // };

        // Thread t1 = new Thread(task, "t1");
        // Thread t2 = new Thread(task, "t2");
        // t1.start();
        // t2.start();
    }
}
