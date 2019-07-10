package com.javaman.concurrency.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author pengzhe
 * @date 2019-07-10 14:30
 */
public class TestFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(12344L);
            return "Hello";
        });
      /*  Thread thread = new Thread(futureTask, "thread-1");
        thread.start();*/

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(futureTask);

        System.out.println("get result--");
        System.out.println(futureTask.get());
    }
}