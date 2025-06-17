package com.nihal.threadlearn.basics;

public class MyThread extends Thread {

    @Override
    public void run() { // cant use `throws InterruptedException` after method cause this method is
                        // overriden
        System.out.println("running state");
        try {
            Thread.sleep(2000); // simulate work
            System.out.println("hiii");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Thread finished execution");
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        System.out.println("Initial state: " + t1.getState()); // NEW
        t1.start();

        System.out.println(t1.getState()); // RUNNABLE (dont confuse this with interface, it's just state name here, actually it's enum )
        // System.out.println(Thread.currentThread().getState()); // RUNNABLE

        for (int i = 0; i < 10; i++) {
            System.out.println("Loop " + i + ": " + t1.getState());
            Thread.sleep(200); // Sleep 200ms between checks
        }

        t1.join(); // Wait for t1 to finish

        System.out.println("Final state after join: " + t1.getState()); // TERMINATED
    }
}
