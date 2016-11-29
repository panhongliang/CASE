package com.phl.reference.passive;

/**
 * Created by Administrator on 2016-08-24.
 */
public class ConstantClass {

    static {
        System.out.println("ConstantClass class init");
        System.getProperties();
    }

    public static final String HELLOWORD="hello world";
}
