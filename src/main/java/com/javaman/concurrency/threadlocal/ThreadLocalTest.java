package com.javaman.concurrency.threadlocal;

/**
 * @author pengzhe
 * @date 2019-02-23 11:18
 * @description
 */

public class ThreadLocalTest {

    static ThreadLocal<String> localVariable = new ThreadLocal<>();

    static void print(String string) {
        //打印当前线程本地内存中localVariable变量的值
        System.out.println(string + "::" + localVariable.get());
       // localVariable.remove();
    }

    public static void main(String[] args) {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadOne LocalVariable");
                print("localVariableThreadOne");
                System.out.println("threadOne remove after::" + localVariable.get());
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                localVariable.set("threadTwo LocalVariable");
                print("localVariableThreadTwo");
                System.out.println("threadTwo remove after::" + localVariable.get());
            }
        });

        threadOne.start();
        threadTwo.start();
    }

}
