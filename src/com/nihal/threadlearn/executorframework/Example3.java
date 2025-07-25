package com.nihal.threadlearn.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example3 {
    public static void main(String[] args)  {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Exception occured: " + e);
            }
            System.out.println("i am the new thread!!"); // this will run after the .isDone() return true
            return 12;
        });


        // when setting false, uncomment  Thread.sleep(1000); so that the new thread get some to get executed
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
           System.out.println("Exception: " + e);
        }
        
        
        // try changing true <-> false

       // future.cancel(false); 

        // true means whether the future is running or not, it will must cancel.
        // if set false then if the task is already running then do not interrupt or cancel. therefore we used thread sleep to get some time for the new thread to get executed

        System.out.println("future.isCancelled() = "+ future.isCancelled());   // if we call cancel() method on future then it isCancelled()s will return true
        System.out.println("future.isDone() = "+ future.isDone()); // isDone return true if the task is either a normal termination or terminated by exception.
        executorService.shutdown();

    }

}
