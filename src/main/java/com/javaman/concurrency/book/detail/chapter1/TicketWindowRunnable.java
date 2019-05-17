package com.javaman.concurrency.book.detail.chapter1;

/**
 * @author pengzhe
 * @date 2018-11-24 12:03
 * @description
 */

public class TicketWindowRunnable implements Runnable {

    private int index = 1;
    private final static int MAX = 50;

    @Override
    public void run() {
        while (index < MAX) {
            System.out.println(Thread.currentThread() + "的号码是:" + index++);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {

        final TicketWindowRunnable runnable = new TicketWindowRunnable();
        Thread thread1 = new Thread(runnable, "一号窗口");
        Thread thread2 = new Thread(runnable, "二号窗口");
        Thread thread3 = new Thread(runnable, "三号窗口");
        Thread thread4 = new Thread(runnable, "四号窗口");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
