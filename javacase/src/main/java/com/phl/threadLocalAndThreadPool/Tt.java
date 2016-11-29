package com.phl.threadLocalAndThreadPool;

/**
 * Created by panhongliang on 16/3/3.
 */
public class Tt {


    public static void main(String[] args) {

       final Tt tt=new Tt();
        new Thread(new Runnable() {
            public void run() {
                MyThreadLocal.set("sys");
                tt.doA();
                tt.doB();
            }
        }).start();
        System.out.println(Thread.currentThread().toString()+MyThreadLocal.get());
    }

    private  void doA(){
        StackTraceElement[] elements=Thread.currentThread().getStackTrace();
          for(StackTraceElement element:elements){
              System.out.println("className:"+element.getClassName()+",fileName:"+element.getFileName()+",methodName:"+element.getMethodName()+",lineNumer:"+element.getLineNumber());
          }
    }
    private   void doB(){
        System.out.println(Thread.currentThread());
        System.out.println(MyThreadLocal.get());
    }
}
