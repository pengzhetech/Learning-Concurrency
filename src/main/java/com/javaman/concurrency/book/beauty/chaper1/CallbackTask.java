package com.javaman.concurrency.book.beauty.chaper1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author pengzhe
 * @date 2019-02-21 21:35
 * @description
 */

public class CallbackTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "Hello";
    }

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new CallbackTask());
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
