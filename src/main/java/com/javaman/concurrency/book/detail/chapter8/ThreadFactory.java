package com.javaman.concurrency.book.detail.chapter8;

/**
 * @author pengzhe
 * @date 2018-11-25 12:53
 * @description
 */
@FunctionalInterface
public interface ThreadFactory {

    Thread creatThread(Runnable runnable);
}
