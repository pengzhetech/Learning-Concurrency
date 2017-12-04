package com.javaman.concurrency.keywords;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:彭哲
 * @Date:2017/12/2 处理器中通过总线锁和缓存锁保证原子性
 * Java中通过锁和CAS保证原子性
 */
public class AtomicTest {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private int i = 0;

    public static void main(String[] args) {
    }
}
