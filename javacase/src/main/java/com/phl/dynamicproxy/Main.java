package com.phl.dynamicproxy;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2016-11-03.
 */
public class Main {
    public static void main(String[] args) {
        MyInferface in=new MyInferfaceImpl();
        MyInferface proxy= (MyInferface) Proxy.newProxyInstance(in.getClass().getClassLoader(),new Class[]{MyInferface.class},new MyInferfaceProxy(in));
        proxy.dosth();
    }
}
