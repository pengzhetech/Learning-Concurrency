package com.javaman.concurrency.book.detail.chapter29;

/**
 * @author pengzhe
 * @date 2018-12-01 18:14
 * @description
 */

public class Event implements Message {
    @Override
    public Class<? extends Message> getType() {
        return getClass();
    }
}
