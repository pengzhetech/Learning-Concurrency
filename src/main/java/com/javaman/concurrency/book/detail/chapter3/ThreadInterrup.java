package com.javaman.concurrency.book.detail.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 18:37
 * @description
 */

public class ThreadInterrup {


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread() + "我被中断了");
            }
            System.out.println("-------------------被打断后的代码");
        });
        thread.setName("名称::子线程");
        thread.start();
        System.out.println("线程组名--" + thread.getThreadGroup().getName());
        TimeUnit.MILLISECONDS.sleep(2);
        System.out.println("被终端前" + thread.isInterrupted());
        thread.interrupt();
        System.out.println("被终端后--------------"+thread.isInterrupted());
    }

}
