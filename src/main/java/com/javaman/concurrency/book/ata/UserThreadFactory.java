package com.javaman.concurrency.book.ata;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author pengzhe
 * @date 2018-12-18 14:49
 * @description
 */

public class UserThreadFactory implements ThreadFactory {

    private final String namePrefix;
    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public UserThreadFactory(String whatFeatureOfFactory) {
        this.namePrefix = "From UserThreadFactory" + whatFeatureOfFactory + "-Worker-";
    }

    @Override
    public Thread newThread(Runnable task) {
        String name = namePrefix + atomicInteger.getAndDecrement();
        Thread thread = new Thread(null, task, name, 0);
        System.out.println(thread.getName());
        return thread;
    }



}
