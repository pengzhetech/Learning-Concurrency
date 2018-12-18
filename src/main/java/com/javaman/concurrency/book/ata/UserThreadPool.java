package com.javaman.concurrency.book.ata;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author pengzhe
 * @date 2018-12-18 15:04
 * @description
 */

public class UserThreadPool {

    public static void main(String[] args) {
        //缓存队列设置固定长度为2,目的为了快速触发rejectHandler
        BlockingQueue queue = new LinkedBlockingQueue(2);

        //假设外部任务线程的来源由机房1和机房2混合调用
        UserThreadFactory factory1 = new UserThreadFactory("第一机房");
        UserThreadFactory factory2 = new UserThreadFactory("第二机房");

        UserRejectHandler handler = new UserRejectHandler();

        //核心线程为1,最大线程为2,也是为了保证触发rejectHandler
        ThreadPoolExecutor threadPoolFirst = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS, queue, factory1, handler);

        ThreadPoolExecutor threadPoolSecond = new ThreadPoolExecutor(1, 2,
                60, TimeUnit.SECONDS, queue, factory2, handler);

        Runnable task = new Task();
        for (int i = 0; i < 300; i++) {
            threadPoolFirst.submit(task);
            threadPoolSecond.submit(task);
        }
    }
}

class Task implements Runnable {
    private final AtomicLong count = new AtomicLong(0L);

    @Override
    public void run() {
        System.out.println("running_" + count.getAndIncrement());
    }
}