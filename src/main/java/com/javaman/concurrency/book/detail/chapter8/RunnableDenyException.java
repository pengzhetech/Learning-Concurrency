package com.javaman.concurrency.book.detail.chapter8;

/**
 * @author pengzhe
 * @date 2018-11-25 18:08
 * @description
 */

public class RunnableDenyException extends RuntimeException {
    public RunnableDenyException(String message) {
        super(message);
    }
}
