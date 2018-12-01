package com.javaman.concurrency.book.detail.chapter29;

/**
 * @author pengzhe
 * @date 2018-12-01 18:05
 * @description
 */

public interface Channel<E extends Message> {

    /**
     * dispatch方法主要负责Message的调度
     *
     * @param message
     */
    void dispatch(E message);
}
