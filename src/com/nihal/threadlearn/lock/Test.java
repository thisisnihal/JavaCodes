package com.nihal.threadlearn.lock;

public class Test {
    public static void main(String[] args) {

        /* example Using syncronized keyword to acquire lock */
        // BankAccount pnb = new BankAccount(100);

        /* using lock object to acquire lock. */
        BankAccount2 pnb = new BankAccount2(100);


        Runnable task = new Runnable() {
            @Override
            public void run() {
                pnb.withdraw(50);
            }
        };


        Thread t1 = new Thread(task, "thread-1");
        Thread t2 = new Thread(task, "thread-2");

        t1.start();
        t2.start();


    }
}
