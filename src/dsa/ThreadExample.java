package dsa;

public class ThreadExample {
    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Producer producer = new Producer(sharedObject);
        Consumer consumer = new Consumer(sharedObject);

        producer.start();
        consumer.start();
    }
}

class SharedObject {
    private int data;
    private boolean isDataAvailable = false;

    // Method to produce data
    public synchronized void produce(int newData) throws InterruptedException {
        while (isDataAvailable) {
            wait(); // Wait until data is consumed
        }
        data = newData;
        isDataAvailable = true;
        System.out.println("Produced: " + newData);
        notify(); // Notify consumer thread
    }

    // Method to consume data
    public synchronized int consume() throws InterruptedException {
        while (!isDataAvailable) {
            wait(); // Wait until data is produced
        }
        isDataAvailable = false;
        System.out.println("Consumed: " + data);
        notify(); // Notify producer thread
        return data;
    }
}

class Producer extends Thread {
    private SharedObject sharedObject;

    public Producer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                sharedObject.produce(i);
                Thread.sleep(1000); // Simulate production time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private SharedObject sharedObject;

    public Consumer(SharedObject sharedObject) {
        this.sharedObject = sharedObject;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                sharedObject.consume();
                Thread.sleep(1000); // Simulate consumption time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
