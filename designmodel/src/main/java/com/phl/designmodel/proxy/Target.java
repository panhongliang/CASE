package com.phl.designmodel.proxy;

/**
 * Created by panhongliang on 16/2/24.
 */
public class Target implements TargetInterface {
    public void dosth(int a, int b) {
        System.out.println("a = " + a);
    }
}
