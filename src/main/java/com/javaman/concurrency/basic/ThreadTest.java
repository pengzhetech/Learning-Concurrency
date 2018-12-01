package com.javaman.concurrency.basic;

/**
 * @author pengzhe
 * @date 2018-11-29 14:11
 * @description
 */

public class ThreadTest {

    public static void main(String[] args) {

        ShareObject shareObject = new ShareObject();

        for (int i = 0; i <300; i++) {
            Thread thread = new Thread(shareObject);
            thread.start();
        }
        System.out.println(shareObject.i);
    }

}
