package com.javaman.concurrency.book.detail.chapter3;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 19:12
 * @description
 */

public class Interrupted {


    public static void main(String[] args) throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(), "SleepRunner");
        sleepThread.setDaemon(true);

        Thread busyThread = new Thread(new BusyRunner(), "BusyRunner");
        busyThread.setDaemon(true);

        sleepThread.start();
        busyThread.start();

        TimeUnit.SECONDS.sleep(5);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleep" + sleepThread.isInterrupted());
        System.out.println("busy" + busyThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);
    }


    static class SleepRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread());
                    e.printStackTrace();
                }
            }
        }
    }

    static class BusyRunner implements Runnable {
        @Override
        public void run() {
            while (true) {
              //  System.out.println("Busy Thread");
            }
        }
    }


}
