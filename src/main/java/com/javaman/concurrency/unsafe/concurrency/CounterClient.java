package com.javaman.concurrency.unsafe.concurrency;

/**
 * @author pengzhe
 * @date 2019-02-24 21:23
 * @description
 */

public class CounterClient implements Runnable {

    private Counter counter;
    private int number;

    public CounterClient(Counter counter, int number) {
        this.counter = counter;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++) {
            counter.increment();
        }
    }
}
