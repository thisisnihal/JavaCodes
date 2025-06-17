package com.nihal.threadlearn.basics;

public class Test {
    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName()); // main
        
        // using class that extends Thread class
        // World world = new World();
        // world.start();

        // using class that implements the Runnable interface
        World2 world2 = new World2();
        Thread t1 = new Thread(world2);
        t1.start();



        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
