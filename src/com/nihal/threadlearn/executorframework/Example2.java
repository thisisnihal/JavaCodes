package com.nihal.threadlearn.executorframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example2 {
    public static void main(String[] args)  throws ExecutionException, InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> callable1 = () -> {
            System.out.println("task 1");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("task 2");
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("task 3");
            return 3;
        };

        List<Callable<Integer>> list = Arrays.asList(callable1, callable2, callable3);
        
        // invokeAll block the main thread(in this case - the current running thread that is executing this function) until it finishes the all
        // so you can notice the "hellow!" will always be printed at last, however the callable task will be invoked at same time
        List<Future<Integer>> futures = executorService.invokeAll(list);

        for (Future future : futures) {
            System.out.println(future.get());   // the return value of callable
        }
        executorService.shutdown();

        System.out.println("hellow!");
    }
}
