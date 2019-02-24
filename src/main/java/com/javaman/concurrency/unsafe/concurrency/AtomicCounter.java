package com.javaman.concurrency.unsafe.concurrency;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author pengzhe
 * @date 2019-02-24 22:00
 * @description
 */

public class AtomicCounter implements Counter {

    AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }
}
