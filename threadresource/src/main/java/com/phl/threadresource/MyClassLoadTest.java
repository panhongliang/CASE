package com.phl.threadresource;

import java.lang.reflect.Method;

/**
 * Created by panhongliang on 16/1/14.
 */
public class MyClassLoadTest {
    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader=new MyClassLoader();
        Class aClass=myClassLoader.findClass("jav.lang.String");
        Method m=aClass.getMethod("test");
        System.out.println(m.invoke(aClass.newInstance()));
        int a=0;
    }
}
