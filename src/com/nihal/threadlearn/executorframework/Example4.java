package com.nihal.threadlearn.executorframework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Example4 {
    public static void main(String[] args) {

        // for schedule task
        ScheduledExecutorService schedular = Executors.newScheduledThreadPool(1);

        // 1.
        // the task goes into the queue immediately for execution
        // so we can call shutdown shutdown in next line
        // -------------------------------------
        // schedular.schedule(
        // () -> System.out.println("task executed after 5 sec delay!"),
        // 5,
        // TimeUnit.SECONDS);
        // schedular.shutdown();
        // ---------------------------------------



        // 2.

        // ------------------------------------
        // schedular.scheduleAtFixedRate(
        //         () -> System.out.println("task executed after every 5 sec"),
        //         5, // initial delay
        //         5, // period
        //         TimeUnit.SECONDS);
        // // for scheduleAtFixedRate, we have to schedule shutdown also to stop running indefinetly
        // schedular.schedule(() -> {
        //     System.out.println("initiatinf shutdown...");
        //     schedular.shutdown();
        // }, 20, TimeUnit.SECONDS);
        // ------------------------------------



        // 3.
        // ------------------------------------
      ScheduledFuture<?> ScheduledFuture =   schedular.scheduleWithFixedDelay(
                () -> System.out.println("task executed after every 5 sec"),
                5, // initial delay
                5, // delay
                TimeUnit.SECONDS);

         schedular.schedule(() -> {
            System.out.println("initiatinf shutdown...");
            schedular.shutdown();
        }, 20, TimeUnit.SECONDS);
        // ------------------------------------
    }   
}
