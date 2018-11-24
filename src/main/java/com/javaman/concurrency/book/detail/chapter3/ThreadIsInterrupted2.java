package com.javaman.concurrency.book.detail.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 18:54
 * @description
 */

public class ThreadIsInterrupted2 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MINUTES.sleep(1);
                    } catch (InterruptedException e) {
                        System.out.println("I am interrupted?" + Thread.currentThread().isInterrupted());
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("Thread is interrupted?" + thread.isInterrupted());
        thread.interrupt();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("Thread is interrupted?" + thread.isInterrupted());
    }
}
