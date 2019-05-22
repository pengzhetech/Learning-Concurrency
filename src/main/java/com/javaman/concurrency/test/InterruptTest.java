package com.javaman.concurrency.test;

import java.util.concurrent.TimeUnit;

/**
 * @auther: pengzhe
 * @since: 2019/5/22 23:15
 * @description:
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {

            System.out.println("我是线程1");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();
                System.out.println("线程1被中断");
                System.out.println("线程1的状态：："+Thread.currentThread().isInterrupted());
            }

            System.out.println("线程1结束");

        });
        thread1.start();

        System.out.println("中断前的状态：：" + thread1.isInterrupted());

        //主线程中断子线程
        thread1.interrupt();
        System.out.println("中断后的状态：：" + thread1.isInterrupted());
        thread1.join();

        System.out.println("main is over");
    }

}
