package com.nihal.threadlearn.lock;

public class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal " + amount);
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }
            balance -= amount;
            System.out.println(
                    Thread.currentThread().getName() + " completed withdrawal, Remaining balance: " + balance);

        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance.");

        }
    }
}
