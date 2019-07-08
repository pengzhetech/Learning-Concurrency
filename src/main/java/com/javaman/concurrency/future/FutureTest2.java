package com.javaman.concurrency.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

/**
 * @auther: pengzhe
 * @since: 2019/7/7 16:58
 * @description:
 */
public class FutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> submit = executorService.submit(new Task());
      String s = submit.get();
       System.out.println("返回值--------->" + s);

        System.out.println("主线程结束!!!!!!");

        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }

    public static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("Callable开始执行!!!");
            System.out.println("睡觉开始"+System.nanoTime());
            Thread.sleep(1000);
            System.out.println("睡觉完成"+System.nanoTime());
            System.out.println("子线程结束");
            return "我是返回值";
        }
    }

}
