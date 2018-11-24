package com.javaman.concurrency.book.detail.chapter1;

/**
 * @author pengzhe
 * @date 2018-11-24 11:41
 * @description
 */

public class TicktWindow extends Thread {

    //柜台名称
    private final String name;

    //每个柜台最多受理50比业务
    private static final int MAX = 50;

    private int index = 1;

    public TicktWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index < MAX) {
            System.out.println("柜台:" + name + "当前的号码是" + index++);
        }
    }

    public static void main(String[] args) {

        TicktWindow window1 = new TicktWindow("1号柜台");
        TicktWindow window2 = new TicktWindow("2号柜台");
        TicktWindow window3 = new TicktWindow("3号柜台");
        TicktWindow window4 = new TicktWindow("4号柜台");

        window1.start();
        window2.start();
        window3.start();
        window4.start();
    }
}
