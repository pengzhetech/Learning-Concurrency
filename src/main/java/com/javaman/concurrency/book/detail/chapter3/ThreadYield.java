package com.javaman.concurrency.book.detail.chapter3;

import java.util.stream.IntStream;

/**
 * @auther: pengzhe
 * @since: 2019/5/22 21:34
 * @description:
 */
public class ThreadYield {

    public static void main(String[] args) {
        //  IntStream.range(0, 2).forEach(System.out::println);

        IntStream.range(0, 2)
                .mapToObj(ThreadYield::create)
                .forEach(Thread::start);
    }

    private static Thread create(int index) {
        return new Thread(() -> {
          /*  if (index == 0) {
                Thread.yield();
            }*/
            System.out.println(index);
        });
    }
}
