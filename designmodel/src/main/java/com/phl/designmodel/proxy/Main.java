package com.phl.designmodel.proxy;

/**
 * Created by panhongliang on 16/2/25.
 */
public class Main {
    public static void main(String[] args) {

        TargetInterface target= (TargetInterface) BeanFactory.getBean(Target.class);
        target.dosth(1,1);
    }
}
