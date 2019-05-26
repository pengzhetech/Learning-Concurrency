package com.javaman.concurrency.random;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @auther: pengzhe
 * @since: 2019/5/26 17:15
 * @description:
 */
public class RandomTest {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream.range(0, 10).forEach((i) -> System.out.println(random.nextInt(50)));
    }
}
