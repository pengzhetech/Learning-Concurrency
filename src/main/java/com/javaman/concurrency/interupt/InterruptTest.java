package com.javaman.concurrency.interupt;

/**
 * @author pengzhe
 * @date 2019-02-23 10:33
 * @description
 */

public class InterruptTest {

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    i++;
                    if (i == 10) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                }
            }
        });
        threadA.start();

        Thread mainThread = Thread.currentThread();

        threadA.interrupt();

    }


}
