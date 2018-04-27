package com.javaman.concurrency.basic;

/**
 * @author pengzhe
 * @date 2018/4/27 11:57
 * @description 锁的重入 只要一个线程持有了某个锁，那么它就可以进入任何被这个锁保护的代码块
 * <p>
 * 这样的重入锁实现起来也简单，可以给每个锁关联一个持有的线程和获取锁的次数，
 * 初始状态下锁的计数值是0，也就是没有被任何线程持有锁，
 * 当某个线程获取这个锁的时候，计数值为1，如果继续获取该锁，
 * 那么计数值继续递增，每次退出一个同步代码块时，计数值递减，直到递减到0为止。
 */

public class SynchronizedDemo {
    private Object lock = new Object();

    public void m1() {
        synchronized (lock) {
            System.out.println("这是第一个方法");
            m2();
        }
    }

    public void m2() {
        synchronized (lock) {
            System.out.println("这是第二个方法");
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        synchronizedDemo.m1();
    }
}
