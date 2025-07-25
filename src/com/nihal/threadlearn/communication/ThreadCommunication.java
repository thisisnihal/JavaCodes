package com.nihal.threadlearn.communication;

class SharedResources {
    private int data;
    private boolean hasData;

    public synchronized void produce(int value) {
        // if you have data then wait for data to be consumed
        while (hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // else assigns the new value for the data and notify the thread
        data = value;
        hasData = true;
        System.out.println("Produced: "+ data);
        notify();
    }
    public synchronized int consume() {
        // if there is no data to consume the simply wait
        while (!hasData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
        // set hasData to false, notify, consume the data
        hasData = false;
        notify();
        System.out.println("Consumed: "+ data);
        return data;

    }
}

class Producer implements Runnable {
    private SharedResources resources;

    public Producer (SharedResources resources) {
        this.resources = resources;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            resources.produce(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResources resources;

    public Consumer (SharedResources resources) {
        this.resources = resources;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = resources.consume();
           
        }
    }
}
public class ThreadCommunication {
    public static void main(String[] args) {
        SharedResources resources = new SharedResources();
        Thread producerThread = new Thread(new Producer(resources));
        Thread consumerThread = new Thread(new Consumer(resources));

        producerThread.start();
        consumerThread.start();
    }
}


/*
OUTPUT:
Produced: 0
Consumed: 0
Produced: 1
Consumed: 1
Produced: 2
Consumed: 2
Produced: 3
Consumed: 3
Produced: 4
Consumed: 4
Produced: 5
Consumed: 5
Produced: 6
Consumed: 6
Produced: 7
Consumed: 7
Produced: 8
Consumed: 8
Produced: 9
Consumed: 9

 */