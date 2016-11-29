package com.phl.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016-11-03.
 */
public class MyInferfaceProxy implements InvocationHandler{
    private Object real;

    public MyInferfaceProxy(Object real) {
        this.real = real;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("MyInferfaceProxy invoke method before");
        Object o= method.invoke(real,args);
        System.out.printf("MyInferfaceProxy invoke method before");
        return o;
    }
}
