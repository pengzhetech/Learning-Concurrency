package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author pengzhe
 * @date 2019-02-24 20:12
 * @description
 */

public class TestUnsafe {

    static Unsafe unsafe;
    static long stateOffset;
    private static volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.staticFieldOffset(TestUnsafe.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestUnsafe testUnsafe = new TestUnsafe();
        boolean andSwapInt = unsafe.compareAndSwapInt(testUnsafe, stateOffset, 0, 1);
        System.out.println(andSwapInt);
    }

}
