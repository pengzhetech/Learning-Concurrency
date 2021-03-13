package com.javaman.concurrency.threadpool;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author pengzhe
 * @date 2020/4/5 18:46
 * @description
 */

public class Test {
    public static void main(String[] args) {
        MyThreadPoolExecutor.threadPoolExecutor.execute(()->{
            try {
                TimeUnit.MINUTES.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        IntStream.rangeClosed(0,10).forEach(index->{
            MyThreadPoolExecutor.threadPoolExecutor.execute(()->{
                try {
                    TimeUnit.MINUTES.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });
        System.out.println("Test");
    }
}
