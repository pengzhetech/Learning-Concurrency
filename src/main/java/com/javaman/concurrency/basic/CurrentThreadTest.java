package com.javaman.concurrency.basic;

/**
 * @author pengzhe
 * @date 2018/4/27 11:06
 * @description
 */

public class CurrentThreadTest {

    public static void main(String[] args) {
        new CurrentThreadTest().shareMethod();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new CurrentThreadTest().shareMethod();
            }
        }, "t111111").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new CurrentThreadTest().shareMethod();
            }
        }, "t2").start();
    }

    public void shareMethod() {
        Thread thread = Thread.currentThread();
        System.out.println("当前正在执行的线程名称：" + thread.getName());
    }
}
