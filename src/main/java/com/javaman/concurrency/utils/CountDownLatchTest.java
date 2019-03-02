package com.javaman.concurrency.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @author:彭哲
 * @Date:2017/12/6
 *
 * CountDownLatch允许一个或多个线程等待其他线程完成处理操作
 * CountDownLatch的构造函数,接收一个int型的参数作为计数器(必须大于0),如果你想等待N个点完成,就传入N
 * 当我们调用CountDownLatch的countDown方法时,N就会减1,CountDownLatch的await方法会阻塞当前线程,直到N变成0
 * 由于countDown可以用在任何地方,所以这里说的N个点可以N个线程,也可以是1个线程的N个执行步骤
 * 用在多线程那个时,只需要把这个CountDownLatch的引用传递到线程里即可
 *
 *
 */
public class CountDownLatchTest {

    static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
              //  countDownLatch.countDown();
                System.out.println(2);
               // countDownLatch.countDown();
            }
        }).start();
       countDownLatch.await();
        System.out.println("3");
    }
}
