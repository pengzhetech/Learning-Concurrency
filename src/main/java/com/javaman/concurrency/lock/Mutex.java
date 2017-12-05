package com.javaman.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author:彭哲
 * @Date:2017/12/5 自定义独占锁
 * Mutex是一个自定义同步组件,他在同一时刻只允许一个线程占有锁
 * Mutex中定义了一个静态内部类,该内部类继承了同步器并实现了独占式获取和释放同步状态
 * 在tryAcquire(int acquires)方法中,如果经过CAS设置成功(同步状态设置为1),则代表获取了同步状态,
 * 而在tryRelease(int releases)方法中,只是将同步状态同步为0,用户使用Mutex时并不会直接和内部同步器打交道
 * 而是调用Mutex提供的方法,在Mutex的实现中,以获取锁的lock()方法为例,只需要在方法内部的实现中调用同步器的模板方法
 * acquire(int arg)即可,当前线程调用该方法获取同步状态失败后会被加入到同步队列中等待,这样就大大降低了实现一个可靠自定义同步组件的门槛
 */
public class Mutex implements Lock {

    /**
     * 静态内部类,自定义同步器
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 是否处于占用状态
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 当状态为0的时候获取锁
         *
         * @param acquires
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquires) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁,将状态设置为0
         *
         * @param releases
         * @return
         */
        @Override
        protected boolean tryRelease(int releases) {
            if (getState() == 0) {
                throw new IllegalArgumentException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 返回一个Condition,每个condition都包含了一个condition队列
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    //仅需要将操作代理到Sync上即可

    private final Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedTheads() {
        return sync.hasQueuedThreads();
    }


}
