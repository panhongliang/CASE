package com.phl.designmodel.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by panhongliang on 16/2/25.
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before..");
        Object result=method.invoke(target, args);
        System.out.println("after...");
        return result;
    }

    public ProxyHandler(Object target) {
        this.target = target;
    }

}
