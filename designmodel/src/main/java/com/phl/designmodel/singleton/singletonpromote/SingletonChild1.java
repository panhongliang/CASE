package com.phl.designmodel.singleton.singletonpromote;

/**
 * Created by panhongliang on 16/1/11.
 */
public class SingletonChild1 extends Singleton {
    public SingletonChild1(){}

    static public SingletonChild1 getInstance(){
       return (SingletonChild1)Singleton.getInstance("SingletonChild1");
    }
}
