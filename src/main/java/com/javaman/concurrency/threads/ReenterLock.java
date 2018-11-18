package com.javaman.concurrency.threads;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengzhe
 * @date 2018/11/18 11:26
 * @description
 */

public class ReenterLock implements Runnable {

    private static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReenterLock reenterLock = new ReenterLock();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
        System.out.println(i);
    }
}
