package com.phl.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by panhongliang on 16/1/22.
 */
public class ThreadLocalMap {
    private static AtomicInteger integer=new AtomicInteger();
    private static ThreadLocal map=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return integer.getAndDecrement()+"";
        }
    };
    public  static  Object  get(){
        return map.get();
    }
    public static  void set(String i){
        map.set(i);
    }
}
