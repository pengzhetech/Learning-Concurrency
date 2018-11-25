package com.javaman.concurrency.book.detail.chapter5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author pengzhe
 * @date 2018-11-25 11:12
 * @description
 */

public class BooleanLock implements Lock {

    private Thread currentThread;
    private boolean locked = false;
    private final List<Thread> blockedList = new ArrayList<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                final Thread tempThread = Thread.currentThread();
                try {
                    if (!blockedList.contains(tempThread)) {
                        blockedList.add(currentThread);
                    }
                    this.wait();
                } catch (InterruptedException e) {
                    blockedList.remove(tempThread);
                    throw e;
                }
            }
            blockedList.remove(currentThread);
            this.locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        synchronized (this) {
            if (mills < 0) {
                this.lock();
            } else {
                long remainingMills = mills;
                long endMills = System.currentTimeMillis() + remainingMills;
                while (locked) {
                    if (remainingMills <= 0) {
                        throw new TimeoutException("can not get lock during" + mills);
                    }
                    if (!blockedList.contains(Thread.currentThread())) {
                        blockedList.add(Thread.currentThread());
                    }
                    this.wait(remainingMills);
                    remainingMills = endMills - System.currentTimeMillis();
                }
                blockedList.remove(Thread.currentThread());
                this.locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread() == currentThread) {
                this.locked = false;
                this.notify();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(blockedList);
    }
}
