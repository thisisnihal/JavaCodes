package com.nihal.threadlearn.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount2 {
    private int balance;
    private final Lock lock = new ReentrantLock(); // by using this `lock` we will access the withdraw method(critical
                                                   // section) in safe manner
    // for a moment you can assume this lock as a 'key' for the witdraw method, if
    // you have the 'key' then you can access the method

    public BankAccount2(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal " + amount);
                        Thread.sleep(500);
                        balance -= amount;
                        System.out.println(
                                Thread.currentThread().getName() + " completed withdrawal, Remaining balance: "
                                        + balance);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt(); // necessary to perform cleanup operation on the method's
                                                            // state. so that other thread know that this thread has been interrupted.
                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance.");

                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock, will try later.");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        if (Thread.currentThread().isInterrupted()) {
            // so benefit of using the Thread.currentThread().interrupt();  here we can run cleanup code etc
            System.out.println("clean up code...");
        }
    }
}

/*
 * 
 * lock.tryLock() will return true or false depending on the availibilty of the
 * lock.
 * it might throw interrupt exception if the other thread has the lock and this
 * thread has to wait for a mentioned time in param
 * 
 * note - we used tryLock, if we had used lock.lock() then it would be like
 * using the syncronized keyword so makes no sense
 * by using tryLock we will wait for certain time to get the lock again
 * In finally block we must unlock it
 */

/*
 * you can change the wait time and sleep time to get the different output.
 * # CASE - 1
 * 'lock.tryLock(1000, TimeUnit.MILLISECONDS)' and 'Thread.sleep(1200)'
 * output:
 * thread-1 attempting to withdraw 50
 * thread-2 attempting to withdraw 50
 * thread-1 proceeding with withdrawal 50
 * thread-2 could not acquire the lock, will try later.
 * thread-1 completed withdrawal, Remaining balance: 50
 * 
 * As you can see 'thread-2' was not able to acquire the lock again cause sleep
 * is 1.2s while the time to acquire the lock again is just 1s
 * 
 * 
 * 
 * 
 * 
 * # CASE - 2
 * 'lock.tryLock(1000, TimeUnit.MILLISECONDS)' and 'Thread.sleep(500)'
 * output:
 * thread-2 attempting to withdraw 50
 * thread-2 proceeding with withdrawal 50
 * thread-1 attempting to withdraw 50
 * thread-2 completed withdrawal, Remaining balance: 50
 * thread-1 proceeding with withdrawal 50
 * thread-1 completed withdrawal, Remaining balance: 0
 * 
 */