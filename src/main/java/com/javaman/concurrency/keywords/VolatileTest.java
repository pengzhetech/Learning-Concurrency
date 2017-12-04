package com.javaman.concurrency.keywords;

/**
 * @author:彭哲 Date:2017/12/1
 * volatile:
 * 1,保证可见性,并不保证原子性(即也会有并发问题)
 * 2,禁止指令重排
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increace() {
        race++;
    }

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args) {

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increace();
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 0) {
            Thread.yield();
        }
        System.out.println(race);

    }

}
