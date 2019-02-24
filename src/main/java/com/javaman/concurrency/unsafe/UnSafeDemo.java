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

        //通过反射得到theUnsafe对应的field对象
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        //设置该Field为可访问
        field.setAccessible(true);
        //通过Field得到该Field对应的具体对象,传入null是因为该Field为static的
        Unsafe unsafe = (Unsafe) field.get(null);
        System.out.println(unsafe);

        //通过allocateInstance直接创建对象
        User user = (User) unsafe.allocateInstance(User.class);
        Class<? extends User> userClass = user.getClass();
        Field name = userClass.getDeclaredField("name");
        Field age = userClass.getDeclaredField("age");
        Field id = userClass.getDeclaredField("id");

        //获取实例变量name和age在内存中的偏移量并设置值
        unsafe.putInt(user, unsafe.objectFieldOffset(age), 18);
        unsafe.putObject(user, unsafe.objectFieldOffset(name), "架构师是怎样炼成的");

        //这里返回User.class
        Object staticFieldBase = unsafe.staticFieldBase(id);
        System.out.println("staticFieldBase:" + staticFieldBase);

        //获取静态变量id的偏移量staticFieldOffset
        long staticFieldOffset = unsafe.staticFieldOffset(userClass.getDeclaredField("id"));
        //获取静态变量的值
        System.out.println("设置前的ID:" + unsafe.getObject(staticFieldBase, staticFieldOffset));

        //设置值
        unsafe.putObject(staticFieldBase, staticFieldOffset, "6666666666");
        //获取静态变量的值
        System.out.println("设置前的ID:" + unsafe.getObject(staticFieldBase, staticFieldOffset));

        System.out.println("输出User:" + user.toString());

        long data = 100;
        byte size = 1;//单位字节
        //调用allocateMemory分配堆外内存,并获取内存地址memoryAddress
        long memoryAddress = unsafe.allocateMemory(size);
        //直接往内存写数据
        unsafe.putAddress(memoryAddress, data);
        //获取指定内存地址的数据
        long addressData = unsafe.getAddress(memoryAddress);
        System.out.println("addressData:" + addressData);


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

