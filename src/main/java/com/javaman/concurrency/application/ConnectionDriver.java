package com.javaman.concurrency.application;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * @author:彭哲
 * @Date:2017/12/5
 * 由于java.sql.connection是一个接口, 最终的实现是由数据库驱动提供来实现的, 考虑到只是个实例, 这里通过
 * 动态代理构造了一个Connection，该Connection仅仅是在commit方法调用时休眠100毫秒
 */
public class ConnectionDriver {

    static class ConnectionHandler implements InvocationHandler {
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.MICROSECONDS.sleep(100);
            }
            return null;
        }
    }

    public static final Connection createConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new ConnectionHandler());
    }
}
