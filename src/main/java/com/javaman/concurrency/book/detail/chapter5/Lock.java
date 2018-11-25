package com.javaman.concurrency.book.detail.chapter5;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author pengzhe
 * @date 2018-11-25 11:09
 * @description
 */

public interface Lock {

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    List<Thread> getBlockedThreads();

}
