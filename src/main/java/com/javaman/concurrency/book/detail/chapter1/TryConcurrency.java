package com.javaman.concurrency.book.detail.chapter1;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 10:59
 * @description
 */

public class TryConcurrency {


    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                browseNew();
            }
        }.start();

        enjoyMusic();
    }


    private static void browseNew() {
        for (; ; ) {
            System.out.println("News");
            sleep(1);
        }
    }

    private static void enjoyMusic() {
        for (; ; ) {
            System.out.println("Music");
            sleep(1);
        }
    }

    private static void sleep(int second) {
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
