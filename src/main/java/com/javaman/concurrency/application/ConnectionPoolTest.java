package com.javaman.concurrency.application;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:彭哲
 * @Date:2017/12/5
 */
public class ConnectionPoolTest {

    static ConnectionPool pool = new ConnectionPool(10);
    /**
     * 保证所有ConnectionRunner能够同时开始
     */
    static CountDownLatch start = new CountDownLatch(1);
    /**
     * main线程将会等待所有ConnectionRunner结束后才继续执行
     */
    static CountDownLatch end;

    static class ConnectionRunner implements Runnable {
        int count;
        AtomicInteger got;
        AtomicInteger noyGot;

        public ConnectionRunner(int count, AtomicInteger got, AtomicInteger noyGot) {
            this.count = count;
            this.got = got;
            this.noyGot = noyGot;
        }

        public static void main(String[] args) throws InterruptedException {
            //线程数量,可以修改线程数量进行观察
            int threadCount = 1000;
            end = new CountDownLatch(threadCount);
            int count = 20;
            AtomicInteger got = new AtomicInteger();
            AtomicInteger notGot = new AtomicInteger();
            for (int i = 0; i < threadCount; i++) {
                Thread thread = new Thread(new ConnectionRunner(count, got, notGot), "ConnectionThread");
                thread.start();
            }
            start.countDown();
            end.await();
            System.out.println("total invoke:" + (threadCount * count));
            System.out.println("got connection:" + got);
            System.out.println("not got connection" + notGot);
        }

        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (count > 0) {
                try {
                    /**
                     *从线程池中获取连接,如果1000ms内无法获得连接,返回null
                     * 分别统计连接获取的数量got和未获取到的数量notGot
                     */
                    Connection connection = pool.fetchConnection(1000);
                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    } else {
                        noyGot.incrementAndGet();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }
    }

}
