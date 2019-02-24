package com.javaman.concurrency.unsafe.concurrency;

import com.javaman.concurrency.unsafe.UnsafeUtil;
import sun.misc.Unsafe;

/**
 * @author pengzhe
 * @date 2019-02-24 21:32
 * @description
 */

public class CASCounter implements Counter{
    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public CASCounter() throws NoSuchFieldException {
        unsafe = UnsafeUtil.getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    @Override
    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this,offset,before,before+1)) {
            before = counter;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }
}
