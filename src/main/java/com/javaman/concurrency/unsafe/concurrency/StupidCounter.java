package com.javaman.concurrency.unsafe.concurrency;

/**
 * @author pengzhe
 * @date 2019-02-24 21:25
 * @description
 */

public class StupidCounter implements Counter {

    private long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
