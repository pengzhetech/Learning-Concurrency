package com.javaman.concurrency.book.detail.chapter29;

/**
 * @author pengzhe
 * @date 2018-12-01 18:09
 * @description 帮助Event找到合适的Channel并传送给他
 */

public interface DynamicRouter<E extends Message> {

    /**
     * 针对每一种Message类型注册相关的Channel,只有找到合适的Channel该Message才会被处理
     *
     * @param messageType
     * @param channel
     */
    void registerChannel(Class<? extends E> messageType, Channel<? extends E> channel);

    /**
     * 为相应的Channel分配Message
     *
     * @param message
     */
    void dispatch(E message);
}
