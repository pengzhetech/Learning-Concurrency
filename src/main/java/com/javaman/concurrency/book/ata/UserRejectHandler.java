package com.javaman.concurrency.book.ata;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author pengzhe
 * @date 2018-12-18 15:02
 * @description
 */

public class UserRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Your task is rejected." + executor.toString());
    }
}
