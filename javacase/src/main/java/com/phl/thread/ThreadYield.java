package com.phl.thread;

/**
 * Created by panhongliang on 16/1/22.
 */
public class ThreadYield {
    public static void main(String[] args) {
        MyThread myThread1=new MyThread("One");
        myThread1.start();
        MyThread myThread2=new MyThread("Two");
        myThread2.start();
    }
}
class MyThread extends Thread {
    String s;
    MyThread(String s){
        this.s=s;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++){

            Thread.yield();//让当前正在执行的线程休息。
            System.out.println(s+":i = " + i);
        }
    }
}