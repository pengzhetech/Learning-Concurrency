package com.javaman.concurrency.unsafe;

/**
 * @author pengzhe
 * @date 2019-02-24 20:18
 * @description
 */

public class NotInitialized {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {

        Test constructorObj = new Test();
        System.out.println("构造函数初始化得到的value值:" + constructorObj.getValue());

        Test reflectObj = Test.class.newInstance();
        System.out.println("反射得到的value值:" + reflectObj.getValue());

        Test unsafeObj = (Test) UnsafeUtil.getUnsafe().allocateInstance(Test.class);
        System.out.println("Unsafe初始化得到的value值:" + unsafeObj.getValue());

    }

}

class Test {
    //为经过构造函数初始化
    private long value;

    public Test() {
        //经过构造函数初始化
        this.value = 1;
    }

    public long getValue() {
        return this.value;
    }
}
