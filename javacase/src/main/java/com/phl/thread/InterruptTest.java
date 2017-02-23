package com.phl.thread;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017-02-23.
 */
public class InterruptTest {

    static {

    }

    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });
       Thread t=new Thread(new Runnable() {
           @Override
           public void run() {
            // Thread.currentThread().interrupted();//清除我的中断状态，这样下面的sleep就不会抛出异常
               try {
                   sleep(1000);
               } catch (InterruptedException e) {
                   System.out.println("t-afterException-isInterrupted:"+Thread.currentThread().isInterrupted());//t 线程抛出异常后，会清除中断状态
                  Thread.currentThread().interrupt();//在清除中断状态后重新设置中断状态
                   System.out.println("t-afterExceptionReInterrupt-isInterrupted:"+Thread.currentThread().isInterrupted());
               }
           }
       });
        t.start();
        t.interrupt();//main线程设置t 线程的中断状态为true,如果此时t线程在sleep,wait，t 线程就会抛出InterruptedException
        System.out.println("t-isInterrupted:"+t.isInterrupted());



    }
}
