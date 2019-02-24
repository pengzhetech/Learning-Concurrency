package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

/**
 * @author pengzhe
 * @date 2019-02-24 22:20
 * @description Unsafe操作堆外内存
 * C++中有malloc，realloc和free方法来操作内存。在Unsafe类中对应为：
 * <p>
 * //分配var1字节大小的内存，返回起始地址偏移量
 * public native long allocateMemory(long var1);
 * //重新给var1起始地址的内存分配长度为var3字节大小的内存，返回新的内存起始地址偏移量
 * public native long reallocateMemory(long var1, long var3);
 * //释放起始地址为var1的内存
 * public native void freeMemory(long var1);
 * <p>
 * 分配内存方法还有重分配内存方法都是分配的堆外内存，返回的是一个long类型的地址偏移量。这个偏移量在你的Java程序中每块内存都是唯一的。
 */

public class DirectMemory {

    public static void main(String[] args) {


        Unsafe unsafe = UnsafeUtil.getUnsafe();
        /**
         * 在堆外分配一个byte
         */
        long allocatedAddress = unsafe.allocateMemory(1L);
        unsafe.putByte(allocatedAddress, (byte) 100);
        byte shortValue = unsafe.getByte(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(shortValue));
        /**
         * 重新分配一个long
         */
        allocatedAddress = unsafe.reallocateMemory(allocatedAddress, 8L);
        unsafe.putLong(allocatedAddress, 1024L);
        long longValue = unsafe.getLong(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(longValue));
        /**
         * Free掉,这个数据可能脏掉
         */
        unsafe.freeMemory(allocatedAddress);
        longValue = unsafe.getLong(allocatedAddress);
        System.out.println(new StringBuilder().append("Address:").append(allocatedAddress).append(" Value:").append(longValue));

    }
}
