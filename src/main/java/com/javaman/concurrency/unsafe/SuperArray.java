package com.javaman.concurrency.unsafe;

import static com.javaman.concurrency.unsafe.UnsafeUtil.getUnsafe;

/**
 * @author pengzhe
 * @date 2019-02-24 21:13
 * @description
 */

public class SuperArray {
    private final static int BYTE = 1;
    private long size;
    private long address;

    public SuperArray(long size) {
        this.size = size;
        address = getUnsafe().allocateMemory(size * BYTE);
    }

    public void set(long i, byte value) {
        getUnsafe().putByte(address + i * BYTE, value);
    }

    public int get(long index) {
        return getUnsafe().getByte(address + index * BYTE);
    }

    public long size() {
        return size;
    }

    /*----------------简单用法---------------------*/

    public static void main(String[] args) {
        long SUPER_SIZE = (long) Integer.MAX_VALUE * 2;
        SuperArray superArray = new SuperArray(SUPER_SIZE);
        System.out.println("superArray::" + superArray.size);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            superArray.set((long) Integer.MAX_VALUE + i, (byte) 3);
            sum += superArray.get((long) Integer.MAX_VALUE + 1);
        }
        System.out.println("sum of 100 elements:"+sum);
    }


}
