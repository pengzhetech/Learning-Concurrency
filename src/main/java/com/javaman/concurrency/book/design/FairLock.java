package com.javaman.concurrency.book.design;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author pengzhe
 * @date 2018-11-20 23:13
 * @description
 */

public class FairLock implements Runnable {

    private static ReentrantLock fairLock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                fairLock.lock();
                System.out.println();
            } finally {

            }
        }

    }
}
