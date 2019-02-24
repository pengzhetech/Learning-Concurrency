package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author pengzhe
 * @date 2019-02-24 22:47
 * @description
 */

public class OperateObject {

    public static void main(String[] args) {


        Unsafe unsafe = UnsafeUtil.getUnsafe();
        /**
         * 获取类的某个对象的某个field偏移地址
         */
        Field f = null;
        try {
            f = SampleClass.class.getDeclaredField("i");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        long iFiledAddressShift = unsafe.objectFieldOffset(f);
        SampleClass sampleClass = new SampleClass();
        //获取对象的偏移地址，需要将目标对象设为辅助数组的第一个元素（也是唯一的元素）。由于这是一个复杂类型元素（不是基本数据类型），它的地址存储在数组的第一个元素。然后，获取辅助数组的基本偏移量。数组的基本偏移量是指数组对象的起始地址与数组第一个元素之间的偏移量。
        Object helperArray[] = new Object[1];
        helperArray[0] = sampleClass;
        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        long addressOfSampleClass = unsafe.getLong(helperArray, baseOffset);
        int i = unsafe.getInt(addressOfSampleClass + iFiledAddressShift);
        System.out.println(new StringBuilder().append(" Field I Address:").append(addressOfSampleClass).append("+").append(iFiledAddressShift).append(" Value:").append(i));

    }
}
