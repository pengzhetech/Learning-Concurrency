package com.javaman.concurrency.book.detail.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 19:32
 * @description
 */

public class InterruptThreadExit {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am start working");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("I am working");
                }
                System.out.println("I will be shutdown");
            }
        });
       /* thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("System will be down");
        thread.interrupt();*/


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("I am start work");
                for (; ; ) {
                    System.out.println("I am working");
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
                System.out.println("I will be shutdown");
            }
        });

        thread2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread2.interrupt();
    }
}
