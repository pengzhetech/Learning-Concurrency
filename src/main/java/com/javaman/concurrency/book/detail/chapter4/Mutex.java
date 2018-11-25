package com.javaman.concurrency.book.detail.chapter4;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 20:44
 * @description
 */

public class Mutex {


    private final static Object MUTEX = new Object();

    public void access() {

        synchronized (MUTEX) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            //   new Thread(mutex::access).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mutex.access();
                }
            }).start();
        }
    }

}
