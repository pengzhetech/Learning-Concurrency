package com.javaman.concurrency.book.detail.chapter3;

/**
 * @author pengzhe
 * @date 2018-11-24 19:20
 * @description
 */

public class InterruptedTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new SonThread(), "SonThread");
        thread.start();
        thread.interrupt();
        System.out.println(thread.isInterrupted());
        System.out.println(Thread.interrupted());
    }


    static class SonThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                //  System.out.println(Thread.interrupted());
                //System.out.println(Thread.currentThread().isInterrupted());
                // System.out.println("Son Thread");
            }
        }
    }

}

