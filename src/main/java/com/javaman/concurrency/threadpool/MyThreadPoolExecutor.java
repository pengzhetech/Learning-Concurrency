package com.javaman.concurrency.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2020/4/5 18:40
 * @description
 */

public class MyThreadPoolExecutor {

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            10, 10, 100L, TimeUnit.HOURS,
            new ArrayBlockingQueue<>(100));

}
