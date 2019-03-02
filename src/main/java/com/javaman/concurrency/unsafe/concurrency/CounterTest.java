package com.javaman.concurrency.unsafe.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2019-02-24 21:26
 * @description
 */

public class CounterTest {

    private static final int NUM_OF_THREAD = 1000;
    private static final int NUM_OF_INCREMENT = 100000;

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREAD);
        //替换多重实现
        Counter counter = new CASCounter();
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_INCREMENT; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENT));
        }
        service.shutdownNow();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("counter result:" + counter.getCounter());
        System.out.println("Time passes in ms:" + (after - before));
    }
}
