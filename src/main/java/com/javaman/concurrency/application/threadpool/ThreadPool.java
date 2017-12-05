package com.javaman.concurrency.application.threadpool;

/**
 * @author:彭哲
 * @Date:2017/12/5
 */
public interface ThreadPool<Job extends Runnable> {

    /**
     * 执行一个Job
     *
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     *
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     */
    void removeWorkers(int num);

    /**
     * 得到正在等待执行的任务数量
     *
     * @return
     */
    int getJobSize();


}
