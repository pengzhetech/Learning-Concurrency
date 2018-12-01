package com.javaman.concurrency.book.detail.chapter29;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pengzhe
 * @date 2018-12-01 18:44
 * @description
 */

public class AsyncEventDispatcher implements DynamicRouter<Event> {

    private final Map<Class<? extends Event>, AsyncChannel> routerTable;

    public AsyncEventDispatcher() {
        this.routerTable = new ConcurrentHashMap<>();
    }

    @Override
    public void registerChannel(Class<? extends Event> messageType, Channel<? extends Event> channel) {
        if (!(channel instanceof AsyncChannel)) {
            throw new IllegalArgumentException("The channel must be AsyncChannel");
        }
        this.routerTable.put(messageType, (AsyncChannel) channel);
    }

    @Override
    public void dispatch(Event message) {
        if (routerTable.containsKey(message.getType())) {
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatchException("Can't match the channel for[" + message.getType() + "] type");
        }
    }

    public void shutdown() {
        routerTable.values().forEach(AsyncChannel::stop);
    }
}
