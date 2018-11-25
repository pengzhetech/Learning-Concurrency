package com.javaman.concurrency.book.detail.chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-25 11:01
 * @description
 * synchronized无法控制阻塞时长
 * synchronized无法被中断
 */

public class SynchronizedDefect {

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDefect synchronizedDefect = new SynchronizedDefect();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDefect.syncMethod();
            }
        }, "T1");
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedDefect.syncMethod();
            }
        }, "T2");
        thread2.start();
        TimeUnit.MILLISECONDS.sleep(2);

        thread2.interrupt();
        System.out.println(thread2.isInterrupted());
        System.out.println(thread2.getState());

    }


    public synchronized void syncMethod() {
        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
