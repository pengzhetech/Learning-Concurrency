/*
package com.javaman.concurrency.lock;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

*/
/**
 * @author:彭哲
 * @Date:2017/12/5 测试ReentrantLock的公平和非公平锁
 *//*

public class FailAndUnfailTest {

    private static Lock failLock = new ReentrantLock2(true);

    private static Lock unfailLock = new ReentrantLock2(false);

    @Test
    public void fail() {
        testLock(failLock);
    }

    @Test
    public void unfail() {
        testLock(unfailLock);
    }


    public void testLock(Lock lock) {
        //启动5个Job
    }


    private static class Job extends Thread {

        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            //连续两次打印当前的Thread和等待队列中的Thread
        }
    }


    private static class ReentrantLock2 extends ReentrantLock {

        public ReentrantLock2(boolean fair) {
            super(fair);
        }

        public Collection<Thread> getQueuedThread() {
            List<Thread> list = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(list);
            return list;
        }
    }


}
*/
