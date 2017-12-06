package com.javaman.concurrency.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author:彭哲
 * @Date:2017/12/6
 */
public class AtomicClassTest {

    static AtomicInteger ai = new AtomicInteger(19);

    @Test
    public void testInteger() {
        System.out.println(ai.incrementAndGet());
        //返回自增前的值
        System.out.println(ai.getAndIncrement());
        System.out.println(ai.get());
    }

    static int[] value = new int[]{1, 2};

    static AtomicIntegerArray aiArray = new AtomicIntegerArray(value);

    @Test
    public void testArray() {
        aiArray.getAndSet(0, 3);
        System.out.println(aiArray.get(0));
        System.out.println(value[0]);
    }


    public static AtomicReference<User> userAtomicReference = new AtomicReference<User>();

    @Test
    public void testAtomicReference() {
        User user = new User("pengzhe", 18);
        userAtomicReference.set(user);
        User updateUser = new User("newPengzhe", 19);
        userAtomicReference.compareAndSet(user, updateUser);
        System.out.println(userAtomicReference.get().getName());
        System.out.println(userAtomicReference.get().getOld());

    }

    static class User {

        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }

    /**
     * 创建原子更新器,并设置需要更新的对象和对象的属性
     */
    public static AtomicIntegerFieldUpdater<Custom> updater =
            AtomicIntegerFieldUpdater.newUpdater(Custom.class, "old");

    @Test
    public void testUpdate() {
        //设置柯南的年龄为10岁
        Custom conan = new Custom("conan", 10);
        //柯南长了一岁,但是还是会输出旧的年龄
        System.out.println(updater.getAndIncrement(conan));
        //输出柯南现在的年龄
        System.out.println(updater.get(conan));
    }


    public static class Custom {

        private String name;
        public volatile int old;

        public Custom(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }


}
