package com.javaman.concurrency.book.detail.chapter8;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-25 19:21
 * @description
 */

public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName()+"is running and done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (true) {
            System.out.println("线程池活跃线程数"+threadPool.getActiveCount());
            System.out.println("线程池队列大小"+threadPool.getQueueSize());
            System.out.println("线程池核心线程数"+threadPool.getCoreSize());
            System.out.println("线程池最大线程数"+threadPool.getMaxSize());
            System.out.println("=======================================");
            TimeUnit.SECONDS.sleep(5);
        }

    }


}
