package com.phl.thread;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * Created by panhl on 2017-05-04.
 * ThreadLocal 在线程池中使用
 * 线程池共用线程，当线程池中的任务执行时，如果不同的任务使用的是同一个线程执行，则从ThreadLocal同取到相同的值
 */
public class ThreadLocalInExecutor {
    static ThreadLocal local=new ThreadLocal();
    static int i=0;
    public static void main(String[] args) {
        ThreadPoolExecutor pool=new ThreadPoolExecutor(1, 1, 1L, TimeUnit.HOURS, new ArrayBlockingQueue<Runnable>(9), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t=new Thread(r,"PPP_"+(i++));
                return t ;
            }
        });
       class FinalI{
           int i;
           public FinalI(int i){
               this.i=i;
           }

           public int getI() {
               return i;
           }

           public void setI(int i) {
               this.i = i;
           }
       }
       final FinalI f=new FinalI(0);
       for(int i=0;i<10;i++){

           pool.submit(new Runnable() {
               @Override
               public void run() {
                   if(f.getI() ==0){
                     f.setI(1);
                       local.set("1");
                   }
                   System.out.println(Thread.currentThread().getName()+":"+local.get());
                   try {
                       sleep(1000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   //local.remove();  使用完后记得remove
               }
           });
       }
    }
}
