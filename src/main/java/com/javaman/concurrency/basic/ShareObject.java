package com.javaman.concurrency.basic;

/**
 * @author pengzhe
 * @date 2018-11-29 14:09
 * @description
 */

public class ShareObject implements Runnable {


    int i = 0;

    @Override
    public void run() {
        i++;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }


}
