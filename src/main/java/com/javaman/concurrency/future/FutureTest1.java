package com.javaman.concurrency.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther: pengzhe
 * @since: 2019/7/7 16:56
 * @description:
 */
public class FutureTest1 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Task());
        }
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("execute!!!");
        }
    }

}
