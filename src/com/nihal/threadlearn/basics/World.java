package com.nihal.threadlearn.basics;

public class World extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
             System.out.println(Thread.currentThread().getName());
        }
    }
}
