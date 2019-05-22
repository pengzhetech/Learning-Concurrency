package com.javaman.concurrency.book.beauty.chaper1;

/**
 * @auther: pengzhe
 * @since: 2019/5/22 22:57
 * @description:
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(() -> {
            for (; ; ) {

            }
        });
        //启动线程
        threadOne.start();
        //设置中断标志
        threadOne.interrupt();
        //获取中断标志
        System.out.println("是否被中断11" + threadOne.isInterrupted());

        //获取中断标识并重置
        System.out.println("是否被中断222" + threadOne.interrupted());

        //获取中断标识并重置
        System.out.println("是否被中断333" + threadOne.interrupted());

        //获取中断标志
        System.out.println("是否被中断444" + threadOne.isInterrupted());

        threadOne.join();

        System.out.println("Main is over");
    }
}
