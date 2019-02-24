package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * @author pengzhe
 * @date 2019-02-24 20:34
 * @description
 */

public class ObjectSize {

    public static long sizeOf(Object object) {

        Unsafe unsafe = UnsafeUtil.getUnsafe();
        HashSet<Field> fields = new HashSet<>();
        Class<?> aClass = object.getClass();
        while (aClass != Object.class) {
            for (Field field : aClass.getDeclaredFields()) {
                if ((field.getModifiers() & Modifier.STATIC) == 0) {
                    fields.add(field);
                }
            }
            aClass = aClass.getSuperclass();
        }
        long maxSize = 0;
        for (Field field : fields) {
            long offset = unsafe.objectFieldOffset(field);
            if (offset > maxSize) {
                maxSize = offset;
            }
        }
        return ((maxSize / 8) + 1) * 8;
    }

    public static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }


}
