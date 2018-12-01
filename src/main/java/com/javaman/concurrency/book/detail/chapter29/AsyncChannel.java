package com.javaman.concurrency.book.detail.chapter29;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pengzhe
 * @date 2018-12-01 18:37
 * @description
 */

public abstract class AsyncChannel implements Channel<Event> {

    //在AsyncChannel中将使用ExecutorService多线程的方式提交给Message
    private final ExecutorService executorService;

    public AsyncChannel() {
        this(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2));
    }

    public AsyncChannel(ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 重写dispatch用final修饰,避免子类重写
     *
     * @param message
     */
    @Override
    public final void dispatch(Event message) {
        executorService.submit(() -> this.handle(message));
    }

    /**
     * 提供抽象方法,供子类实现具体的message处理
     *
     * @param message
     */
    protected abstract void handle(Event message);

    void stop() {
        if (null != executorService && !executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
