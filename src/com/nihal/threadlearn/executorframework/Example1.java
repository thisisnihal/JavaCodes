package com.nihal.threadlearn.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example1 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int N = 10;

        // -------------------------------------------------
        // 1. this section is without threading
        // -------------------------------------------------

        // for (int i = 0; i < N; i++) {
        // long result = factorial(i); // it will take a lot of time
        // }



        // -------------------------------------------------
        // 2. using threads manually
        // -------------------------------------------------

        // Thread[] threads = new Thread[N];
        // for (int i = 0; i < N; i++) {
        //     int finalI = i; //
        //     threads[i - 1] = new Thread(
        //             () -> {
        //                 long result = factorial(finalI);
        //                 System.out.println(result);
        //             });
        //     threads[i - 1].start();
        // }
        // // all child thread need to be executed and finished therefore use join
        // for (Thread thread : threads) {
        //     try {
        //         thread.join();
        //     } catch (InterruptedException e) {
        //         Thread.currentThread().interrupt();
        //     }
        // }




        // -------------------------------------------------
        // 3. using executorService (no need to create thread manually)
        // -------------------------------------------------

        ExecutorService executor = Executors.newFixedThreadPool(9); // we can use 3 threads also, we can manage resources 
        for (int i = 0; i < N; i++) {
            int finalI = i; //
            // it also expects an object of Runnable type, so here also we can use this lambda exp
            executor.submit(
                    () -> {
                        long result = factorial(finalI);
                        System.out.printf("factorial(%d) = %d\n", finalI, result);
                    });
        }

        executor.shutdown(); // so that we reuse it again
        // note - the above shutdown function wont make the next instruction wait.
        // this might lead to immediate exection of the total time taken statement, which will be the wrong output.
        // in order to solve this, we will use awaitTermination, it blocks all tasks untill executions
        // it returns true when executor has terminated, so we will wait indefintely until executor is terminated
        try {
            while(!executor.awaitTermination(1, TimeUnit.SECONDS)) {    // like join in Thread
                System.out.println("waiting...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        
        // print total time taken
        System.out.println("Total time taken: " + (System.currentTimeMillis() - startTime));
    }

    static long factorial(int n) {
        try {
            Thread.sleep(1000); // we are making the factorial function deliberately slow
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
