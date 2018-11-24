package com.javaman.concurrency.book.detail.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 18:46
 * @description
 */

public class ThreadIsInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1);
        System.out.printf("iInterrupted ? %s\n", thread.isInterrupted());
        thread.interrupt();
        System.out.println(thread.isInterrupted());

    }
}
