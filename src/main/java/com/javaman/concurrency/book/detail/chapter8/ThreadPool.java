package com.javaman.concurrency.book.detail.chapter8;

/**
 * @author pengzhe
 * @date 2018-11-25 12:49
 * @description
 */

public interface ThreadPool {

    void execute(Runnable runnable);

    void shutdown();

    int getInitSize();

    int getMaxSize();

    int getCoreSize();

    int getQueueSize();

    int getActiveCount();

    boolean isShutDown();
}
