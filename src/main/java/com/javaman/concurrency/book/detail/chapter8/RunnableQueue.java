package com.javaman.concurrency.book.detail.chapter8;

/**
 * @author pengzhe
 * @date 2018-11-25 12:52
 * @description
 */

public interface RunnableQueue {

    void offer(Runnable runnable);

    Runnable take() throws InterruptedException;

    int size();
}
