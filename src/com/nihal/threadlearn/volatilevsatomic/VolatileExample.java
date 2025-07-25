package com.nihal.threadlearn.volatilevsatomic;

public class VolatileExample {
    public static void main(String[] args) throws InterruptedException {
        SharedResources sharedResources = new SharedResources();
        Thread writerThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            sharedResources.setFlagTrue();
        });

        Thread readerThread = new Thread(() -> sharedResources.printIfFlagIsTrue());

        writerThread.start();
        readerThread.start();
        writerThread.join();
        readerThread.join();
    }

}


class SharedResources {
    
    // private boolean flag = false;
    private volatile boolean flag = false;

    public void setFlagTrue () {
        System.out.println("writer thread made the flag true");
        flag = true;
    }
    public void printIfFlagIsTrue() {

        while (!flag) {
            // do nothing
        }
        System.out.println("Flag is true!");
    }
}



/*
case 1: without volatile keyword
In this the each thread have cache value of the field, so the field 'flag' is changed by writerThread after 1s 
but the readerThread has the cached value of field 'flag'.
this cache thing is for performance reason
therefore in this case it prints only this: "writer thread made the flag true"
and it is now stuck in inifinite loop

case 2: using volatile keyword
In this case we are telling that take this field from in-memory, do not cache this field
so therefore in this case it prints: 
`writer thread made the flag true
Flag is true!`

it doesnt stuck in the loop this time


we usually volatile for booleans

 */