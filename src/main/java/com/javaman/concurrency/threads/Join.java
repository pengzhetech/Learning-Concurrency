package com.javaman.concurrency.threads;

/**
 * @author:彭哲
 * @Date:2017/12/4 如果一个线程A执行了thread.jon()方法i:当前线程A等待thread线程终止之后才从thread.join()返回.
 * <p>
 * 从输出结果可以看出,每个线程终止的前提是前驱线程的终止,每个线程的等待前驱线程终止后,才从join()方法返回
 */
public class Join {


    public static void main(String[] args) {

        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            //每个线程拥有前一个线程的引用,需要等待前一个线程终止,才能从等待中返回
            Thread thread = new Thread(new Domio(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
    }


    static class Domio implements Runnable {
        private Thread thread;

        public Domio(Thread thread) {
            this.thread = thread;
        }

        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "terminate");
        }
    }


}
