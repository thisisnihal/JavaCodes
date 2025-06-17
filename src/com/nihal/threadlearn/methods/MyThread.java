package com.nihal.threadlearn.methods;

public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("thread is running");
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();
        t1.join(); // when calls join, it will make sure to exectue the t1 first
        System.out.println("hello");
    }
}
