package com.javaman.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author:彭哲
 * @Date:2017/12/4 中断
 */
public class Interrupted {

    public static void main(String[] args) throws InterruptedException {
        //sleepThread不停的尝试睡眠
        Thread sleepThread = new Thread(new SleepRunner(), "SleepThread");
        sleepThread.setDaemon(true);
        //busyThread不停的运行
        Thread busyThread = new Thread(new BusyRunner(), "BusyThread");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();
        //休眠5秒让sleepThread,busyThread充分运行
        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("SleepThread interrupted is" + sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is" + busyThread.isInterrupted());
        //防止SleepThread,BusyThread立刻退出
        SleepUtils.second(2);
    }


    static class SleepRunner implements Runnable {

        public void run() {
            while (true) {
                SleepUtils.second(10);
            }
        }
    }

    static class BusyRunner implements Runnable {
        public void run() {
            while (true) {
//                System.out.println("BusyThread");
            }
        }
    }


}
