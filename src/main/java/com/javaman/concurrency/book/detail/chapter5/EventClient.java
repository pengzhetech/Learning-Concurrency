package com.javaman.concurrency.book.detail.chapter5;

import java.util.concurrent.TimeUnit;

/**
 * @author pengzhe
 * @date 2018-11-24 21:54
 * @description
 */

public class EventClient {
    public static void main(String[] args) {

        final EventQueue eventQueue = new EventQueue();


        new Thread(() -> {
            while (true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "producer").start();


        new Thread(() -> {
            while (true) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "consumer").start();
    }

}
