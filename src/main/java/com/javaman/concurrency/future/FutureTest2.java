package com.javaman.concurrency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @auther: pengzhe
 * @since: 2019/7/7 16:58
 * @description:
 */
public class FutureTest2 {
    public static void main(String[] args) throws InterruptedException,
            ExecutionException {
        List<Future<String>> results = new ArrayList<Future<String>>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            results.add(executorService.submit(new Task()));
        }
        for (Future<String> future : results) {
            System.out.println(future.get());
        }

        System.out.println("Main complete");

        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("execute!!!");
            return "complete";
        }
    }

}
