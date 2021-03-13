package com.javaman.concurrency.memorymodle;

import lombok.SneakyThrows;

/**
 * @author pengzhe
 * @date 2021/3/10 07:59
 * @description 可见性
 */

public class Visibility implements Runnable {

    private boolean isContinuePrint = true;

    @Override
    public void run() {
        while (isContinuePrint) {
            System.out.println("begin ThreadName: " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("end ThreadName: " + Thread.currentThread().getName());
    }

    public boolean isContinuePrint() {
        return isContinuePrint;
    }

    public void setContinuePrint(boolean continuePrint) {
        isContinuePrint = continuePrint;
    }

    @SneakyThrows
    public static void main(String[] args) {
        Visibility printString = new Visibility();
        Thread thread = new Thread(printString, "Thread-A");
        thread.start();
        Thread.sleep(100);
        System.out.println("我要停止它！" + Thread.currentThread().getName());
        printString.setContinuePrint(false);
        System.out.println("end ThreadName: " + Thread.currentThread().getName());

    }
}
