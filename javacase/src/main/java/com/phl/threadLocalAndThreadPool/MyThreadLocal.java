package com.phl.threadLocalAndThreadPool;

/**
 * Created by panhongliang on 16/3/3.
 */
public class  MyThreadLocal {
    private final  static ThreadLocal mylocal=new ThreadLocal();
    public static void set(Object t){
        mylocal.set(t);
    }
    public static Object get(){
        return mylocal.get();
    }
}
