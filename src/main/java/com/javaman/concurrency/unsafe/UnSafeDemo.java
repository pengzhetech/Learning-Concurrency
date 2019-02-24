package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author pengzhe
 * @date 2019-02-24 16:02
 * @description
 */

public class UnSafeDemo {


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);


        User user = (User) unsafe.allocateInstance(User.class);
        Class<? extends User> userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field id = userClass.getDeclaredField("id");

        unsafe.putInt(user, unsafe.objectFieldOffset(age), 18);
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "架构师是怎样炼成的");

        Object staticFieldBase = unsafe.staticFieldBase(id);
        System.out.println("staticFieldBase:"+staticFieldBase);

        long staticFieldOffset = unsafe.staticFieldOffset(userClass.getDeclaredField("id"));
        System.out.println("设置前的ID:"+unsafe.getObject(staticFieldBase,staticFieldOffset));

        unsafe.putObject(staticFieldBase,staticFieldOffset,"6666666666");

        System.out.println("设置前的ID:"+unsafe.getObject(staticFieldBase,staticFieldOffset));

        System.out.println("输出User:"+user.toString());

        long data = 100;
        byte size = 1;

        long memoryAddress = unsafe.allocateMemory(size);
        unsafe.putAddress(memoryAddress,data);

        long addressData = unsafe.getAddress(memoryAddress);
        System.out.println("addressData:"+addressData);


    }


    static class User {
        private String name;
        private int age;
        private static String id = "user_id";

        public User() {
            System.out.println("User类的构造方法被调用");
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

