package com.javaman.concurrency.unsafe.concurrency;

/**
 * @author pengzhe
 * @date 2019-02-24 21:37
 * @description
 */

public class SyncCounter implements Counter {

    private long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public synchronized long getCounter() {
        return counter;
    }
}
