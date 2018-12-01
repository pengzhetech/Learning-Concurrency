package com.javaman.concurrency.book.detail.chapter29;

import java.util.HashMap;
import java.util.Map;

/**
 * @author pengzhe
 * @date 2018-12-01 18:15
 * @description
 */

public class EventDispatcher implements DynamicRouter {


    private final Map<Class<? extends Message>, Channel> routerTable;

    public EventDispatcher() {
        this.routerTable = new HashMap<>();
    }

    @Override
    public void registerChannel(Class messageType, Channel channel) {
        routerTable.put(messageType, channel);
    }

    @Override
    public void dispatch(Message message) {
        if (routerTable.containsKey(message.getType())) {
            //直接获取对应的Channel处理Message
            routerTable.get(message.getType()).dispatch(message);
        } else {
            throw new MessageMatchException("Can't match the channel for[" + message.getType() + "]type");
        }
    }
}
