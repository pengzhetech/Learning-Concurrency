package com.javaman.concurrency.container;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author:彭哲
 * @Date:2017/12/6
 *
 * HashMap在并发执行put操作时,会引起死循环,因为多线程会导致HashMap的Entry链表形成环形数据结构
 * 一旦形成环形数据结构,Entry的next节点永远都不会为空,就会产生死循环获取Entry
 *
 * HashTable容器使用synchronized来保证线程安全,但是线程竞争激烈的情况下,HashTable的效率非常低.
 * 因为当一个线程访问HashTable的同步方法,其他线程也访问HashTable的同步方法时,就会进入阻塞或轮询状态
 * 如线程1使用put操作进行元素添加,线程2不但不能使用put操作添加元素,也不能使用get操作来获取元素
 *
 * ConcurrentHashMap的锁分段技术可有效的提高并发访问效率
 * HashTable容器在竞争激烈的并发环境下表现效率低下的原因是所有访问HashTable的线程都必须竞争同一把锁
 * 假如容器里有多把锁,每一把锁用于锁容器的其中一部分数据,那么当多线程访问容器里不同数据段的时候,线程间就不会存在锁竞争
 * 从而可以有效的提提高并发访问效率,这就是ConcurrentHashMap所使用的锁分段技术
 * 首先将数据分成一段一段的存储,然后给每一段数据配上一把锁,当一个线程占用锁访问其中的一个段数据时
 * 其他段的数据也能被其他线程访问
 *
 */
public class HashMapTest {

    static final HashMap<String, String> map = new HashMap<String, String>(2);

    @Test
    public void test() throws InterruptedException {

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }


}
