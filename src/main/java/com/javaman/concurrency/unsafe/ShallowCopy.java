package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;

/**
 * @author pengzhe
 * @date 2019-02-24 20:41
 * @description
 */

public class ShallowCopy {

    public static void main(String[] args) {
        User user = new User("UserName", 2);
        System.out.println(user);
        User copy = (User) ShallowCopy.shallowCopy(user);
        System.out.println(copy);
    }

    static Object shallowCopy(Object object) {
        long size = sizeOf(object);
        long start = toAddress(object);
        long address = getUnsafe().allocateMemory(size);
        getUnsafe().copyMemory(start, address, size);
        return fromAddress(address);
    }

    static long toAddress(Object object) {
        Object[] array = new Object[]{object};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        return normalize(getUnsafe().getInt(array, baseOffset));
    }

    static Object fromAddress(long address) {
        Object[] array = new Object[]{null};
        long baseOffset = getUnsafe().arrayBaseOffset(Object[].class);
        getUnsafe().putLong(array, baseOffset, address);
        return array[0];
    }

    static long normalize(int value) {
        if (value >= 0) return value;
        return (~0L >>> 32) & value;
    }


    static long sizeOf(Object object) {

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


    static Unsafe getUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

}
