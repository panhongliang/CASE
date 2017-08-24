package com.phl.threadpool;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * Created by panhl on 2017-06-02.
 */
public class ThreadPoolTaskTest {


    public static void main(String[] args) throws Exception{
        ExecutorService s=Executors.newFixedThreadPool(1);

        final  A a=new A();
        a.setSs("=================");
        Future f=s.submit(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (i<10000000000000000L){
                    i++;
                    try {
                        sleep(1);//调用f.cancel(true)可以被打断
                        if(Thread.currentThread().isInterrupted()){ //使程序可以被终止
                            throw new RuntimeException("interrupted");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                    System.out.println(a.getSs());
                }
            }
        });
        s.shutdown();//  不加这个会导致程序不能结束，等待新的任务加入
        try {
            /**
             * 获取结果时超时了。只是等待的线程抛出异常，执行任务的线程并不会中止
             */
            f.get(1000, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            int k=0;
            boolean flag=true;
            while (flag){
                LockSupport.parkNanos(500);
                k++;
                a.setSs(k+"=======================");
                if(k>1000){
                    flag=false;
                }
            }
            /**
             * f.cancel() cancel一个正在执行的任务
             * s.shutdownNow() ,停止所有正在执行的任务
             * 以上两个方法都是通过interrupt线程的sleep,join ,wait 方法来实现的
             * 如果这个任务中没有没用sleep.wait,join 方法，这个任务将取消不了，直到这个任务运行结束
             */
            f.cancel(true);
            //s.shutdownNow();
            a.setSs("cance"+a.getSs());
        }

    }

    public static class  A{
        volatile String ss;

        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
        }
    }
}