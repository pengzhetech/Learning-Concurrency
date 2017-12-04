package com.javaman.concurrency.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author:彭哲
 * @Date:2017/12/4
 */
public class SleepUtils {

   public static final void second(long seconds) {
       try {
           TimeUnit.SECONDS.sleep(seconds);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
}
