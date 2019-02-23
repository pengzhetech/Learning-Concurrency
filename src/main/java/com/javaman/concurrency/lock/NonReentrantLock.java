package com.javaman.concurrency.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author pengzhe
 * @date 2019-02-23 22:21
 * @description 基于AQS实现自定义同步器
 * 自定义AQS需要重写一系列函数,还需定义原子变量state的含义
 * 这里我们定义state为0表示目前锁没有被线程持有,state为1表示锁已经被某一个线程持有
 * 由于锁是不可重入锁,所以不需要记录持有锁的线程获取锁的次数.另外,我们自定义的锁支持条件变量
 */

public class NonReentrantLock implements Lock, Serializable {

    //内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {
        //是否锁已经被持有
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //如果state为0,尝试获取锁
        public boolean tryAcquire(int acquires) {
            assert acquires == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁,设置state为0
         *
         * @param releases
         * @return
         */
        public boolean tryRelease(int releases) {
            assert releases == 1;
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }


        Condition newCondition() {
            return new ConditionObject();
        }

    }

    /**
     * 创建一个Sunc做具体的工作
     */
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
}

