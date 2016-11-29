package com.phl.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2016-10-13.
 */
public class ReentrantLockTest {
    ReentrantLock lock=new ReentrantLock(true);

    public static void main(String[] args) {
        final ReentrantLockTest t= new ReentrantLockTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.doA("1");
                System.out.printf("1 over");
            }
         }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.doA("2");
                System.out.printf("2 over");
            }
        }).start();
    }
    volatile  boolean first=true;
    public void doA(String t){
        lock.lock();
        try {
                if(first){
                    first=false;
                    while ( true){
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("=====doA"+t+"===");
                    }
                }

        }finally {
            lock.unlock();
        }
    }
}
