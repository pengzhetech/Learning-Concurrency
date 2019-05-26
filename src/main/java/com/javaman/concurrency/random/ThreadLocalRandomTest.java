package com.javaman.concurrency.random;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @auther: pengzhe
 * @since: 2019/5/26 17:27
 * @description:
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //    IntStream.range(0, 10).forEach(i -> System.out.println(random.nextInt(5)));


        AtomicInteger integer = new AtomicInteger(0);
        IntStream.range(0, 20).forEach(jjjj -> {
            int index = ThreadLocalRandom.current().nextInt(5);
            if (index <= 3) {
                integer.incrementAndGet();
                System.out.println(index);
                System.out.println("Yes");
            }
        });
        System.out.println(integer);

    }
}
