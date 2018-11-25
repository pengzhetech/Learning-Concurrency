package com.javaman.concurrency.book.detail.chapter5;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

/**
 * @author pengzhe
 * @date 2018-11-25 11:28
 * @description
 */

public class BooleanLockTest {

    private final Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread() + "get the lock");
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void syncMethodTimeoutable() {
        try {
            lock.lock(1000);
            System.out.println(Thread.currentThread() + "get the lock");
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest lockTest = new BooleanLockTest();
        // IntStream.range(0, 10).mapToObj(i -> new Thread(lockTest::syncMethod)).forEach(Thread::start);


      /*  new Thread(lockTest::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(20);

        Thread thread = new Thread(lockTest::syncMethod, "T2");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();*/

        new Thread(lockTest::syncMethod,"T1").start();
        TimeUnit.MILLISECONDS.sleep(2);

        Thread thread = new Thread(lockTest::syncMethodTimeoutable, "T2");
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);



    }


}
