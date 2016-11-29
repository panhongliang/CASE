package com.phl.test;

/**
 * Created by panhongliang on 15/8/25.
 */
public class T2 {
    String a;
    String b;
    T0 t0;

    public T2(String a,String b,T0 t0){
        this.a=a;
        this.b=b;
        this.t0=t0;
    }
    public void doA(){
        throw new RuntimeException();//非检查性异常
    }
}
