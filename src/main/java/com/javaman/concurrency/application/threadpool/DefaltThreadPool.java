package com.javaman.concurrency.application.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author:彭哲
 * @Date:2017/12/5
 *
 * 线程池的本质就是使用了一个线程安全的工作队列连接工作者线程和客户端线程,客户端线程将任务放入工作队列中后便返回,
 * 而工作者线程则不断的从工作队列上取出工作并执行当工作,当工作队列为空时,所有的工作线程均等待在工作队列上
 * 当有客户端提交一个任务之后会通知任意一个工作线程,随着大量的任务被提交,更多的工作者线程会被唤醒
 */
public class DefaltThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //线程池最大限制数
    private static final int MAX_WORKER_NUMBERS = 10;

    //线程池默认的线程数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    //线程池最小的数量
    private static final int MIN_WORKER_NUMBERS = 1;

    /**
     * 这是一个工作列表,将会向里面插入工作
     */
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    /**
     * 工作者列表
     */
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    /**
     * 工作者线程的数量
     */
    private int workNum = DEFAULT_WORKER_NUMBERS;

    /**
     * 线程编号的生成
     */
    private AtomicLong threadNum = new AtomicLong();

    public DefaltThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaltThreadPool(int num) {
        workNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS
                : num < MIN_WORKER_NUMBERS ? MIN_WORKER_NUMBERS : num;
        initializeWorkers(workNum);
    }

    /**
     * 初始化线程工作者
     *
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-Worker-" + threadNum.incrementAndGet());
            thread.start();
        }

    }


    public void execute(Job job) {
        if (job != null) {
            //添加一个工作,然后进行通知
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs) {
            //限制新增的Workers数量不能超过最大值
            if (num + this.workNum > MAX_WORKER_NUMBERS) {
                num = MAX_WORKER_NUMBERS - this.workNum;
            }
            initializeWorkers(num);
            this.workNum += num;
        }
    }

    public void removeWorkers(int num) {
        synchronized (jobs) {
            if (num >= this.workNum) {
                throw new IllegalArgumentException("beyond workNum");
            }
            //按照给定的数量停止Worker
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workNum -= count;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    /**
     * 工作者,负责消费任务
     */
    class Worker implements Runnable {

        /**
         * 是否工作
         */
        private volatile boolean running = true;

        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    //如果工作列表是空的,那么就wait,并且释放锁
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知外部对WorkThread的中断操作,返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    //取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {
                        job.run();
                    } catch (Exception e) {
                        //忽略job执行的Exception
                        e.printStackTrace();
                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }


}
