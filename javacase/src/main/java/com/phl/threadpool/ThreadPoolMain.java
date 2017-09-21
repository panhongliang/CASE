package com.phl.threadpool;

import com.phl.threadLocalAndThreadPool.T;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @Title:ThreadPoolMain
 * @Description:
 * @author:panhl
 * @date 2017/7/17 0017 18:42
 */
public class ThreadPoolMain {
    static ExecutorService service= Executors.newFixedThreadPool(4);
    public static void main(String[] args) {
       // cancel();
        for(int i=0;i<100;i++){
            run();
        }

      //  System.out.println("==========");
    }
    private static void run(){
        service.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("run");
            }
        });
    }
    private static void cancel(){
        ExecutorService service= Executors.newFixedThreadPool(1);
        //提交一个任务
        Future futureTask= service.submit(new Runnable() {
            @Override
            public void run() {
                int i=0;
                while (true){
                    try {
                        sleep(1);//象征性的睡1毫秒
                        System.out.println("============:"+i++);//打印==到控制台，判断任务还在运行
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }

            }
        });
        service.shutdown();//不再接收新的任务
        try {
            sleep(1000);
            futureTask.cancel(true);//取消任务
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
