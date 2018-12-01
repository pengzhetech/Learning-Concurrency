package com.javaman.concurrency.book.detail.chapter29;

/**
 * @author pengzhe
 * @date 2018-12-01 18:05
 * @description
 */

public interface Message {

    Class<? extends Message> getType();

}
