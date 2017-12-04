package com.javaman.concurrency.threads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author:彭哲
 * @Date:2017/12/4
 * 1.使用wait(),notify(),notifyAll()时需要先对调用对象加锁
 * 2.调用wait()方法后线程状态由RUNNING变成WAITING,释放锁,并将当前线程放置到对象的等待队列中
 * 3.notify(),notifyAll()方法调用后,等待线程依然不会从wait()返回,
 * 需要调用notify(),notifyAll()的线程释放锁之后,等待线程才会有机会从wait()中返回
 * 4.notify()方法将等待队列中的一个等待线程从等待队列中移步到同步队列中,而notifyAll()方法则是将
 * 等待队列中所有的线程全部移步到同步队列中,被移动的线程状态由WAITING变成BLOCKED
 * 5.wait()方法返回的前提是获得了调用对象的锁
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {
        public void run() {
            //加锁,拥有lock的Monitor
            synchronized (lock) {
                //当条件不满足时,继续wait,同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + "flag is true.wait@" +
                                new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //当条件满足时,完成工作
                System.out.println(Thread.currentThread() + "flag is false.running@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements Runnable {

        public void run() {
            //加锁,拥有lock的Monitor
            synchronized (lock) {
                /**
                 * 获取lock的锁,然后进行通知,通知时不会释放lock的锁
                 * 直到当前线程释放了lock后,WaitThread才能从wait方法中返回
                 */
                System.out.println(Thread.currentThread() + "hold lock.notify@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(1);
            }
            //再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + "hold lock again.sleep@" +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(1);
            }
        }
    }

}
