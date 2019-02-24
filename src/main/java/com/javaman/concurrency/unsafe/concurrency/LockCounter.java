package com.javaman.concurrency.unsafe.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

/**
 * @author pengzhe
 * @date 2019-02-24 21:39
 * @description
 */

public class LockCounter implements Counter {
    private long counter = 0;
    private WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
    @Override
    public void increment() {
        writeLock.lock();
        counter++;
        writeLock.unlock();
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
