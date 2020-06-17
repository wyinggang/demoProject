package com.demo;

import java.util.concurrent.*;

public class TreadPoolExcutorDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,20,100,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );


        Executors.newFixedThreadPool(2);

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("----------------");
                return "null";
            }
        };


        FutureTask<String> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        System.out.println(futureTask.get());
        thread.interrupt();


        Future<String> future = executor.submit(callable);

        try {
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }



        for (int i= 0;i<10;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");

    }

}
