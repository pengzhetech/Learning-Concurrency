package com.javaman.concurrency.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;


public class ChangePrivateField {

    public static void main(String[] args) throws NoSuchFieldException {

        PrivateFieldClass fieldClass = new PrivateFieldClass();
        System.out.println(fieldClass.giveAccess());  //false

        Unsafe unsafe = UnsafeUtil.getUnsafe();
        Field field = fieldClass.getClass().getDeclaredField("accessAllowed");
        unsafe.putInt(fieldClass, unsafe.objectFieldOffset(field), 666);
        System.out.println(fieldClass.giveAccess());

    }
}

class PrivateFieldClass {

    private int accessAllowed = 1;

    public boolean giveAccess() {
        return 666 == accessAllowed;
    }
}