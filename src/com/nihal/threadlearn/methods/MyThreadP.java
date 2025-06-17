package com.nihal.threadlearn.methods;

public class MyThreadP extends Thread {

    public MyThreadP(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /* priority */
        // MyThreadP l = new MyThreadP("LOW");
        // MyThreadP m = new MyThreadP("MED");
        // MyThreadP h = new MyThreadP("HIGH");

        // l.setPriority(Thread.MIN_PRIORITY);
        // m.setPriority(Thread.NORM_PRIORITY); // this rule is not strict, it may
        // happen that jvm execute MIN first then NORM
        // h.setPriority(Thread.MAX_PRIORITY);

        // l.start();
        // m.start();
        // h.start();


        /* Interrrupt */
        // MyThreadI t2 = new MyThreadI("my-thread-I");
        // t2.start();
        // t2.interrupt(); // it will interrupt the sleep method

        /* Yield */
        // MyThreadY t3 = new MyThreadY("my-thread-y-0");
        // MyThreadY t4 = new MyThreadY("my-thread-y-1");
        // t3.start();
        // t4.start();


        /* Daemon Thread */
        MyThreadD t5 = new MyThreadD("daemon-thread-0");
        t5.setDaemon(true);
        t5.start();
        System.out.println("Main done"); // it prints this first then it runs the run method of the daemon thread for just a moment
        // as soon as the main thread(user thread) is done executing, it terminates the daemon thread(background thread) also
    }
}

class MyThreadI extends Thread {
    public MyThreadI(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is running");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

class MyThreadY extends Thread {
    public MyThreadY(String name) {
        super(name);
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.yield(); // it gives chance to other thread also to get executed
        }

    }
}


class MyThreadD extends Thread {
    public MyThreadD(String name) {
        super(name);
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("helloww!!..");
        }
    }
}