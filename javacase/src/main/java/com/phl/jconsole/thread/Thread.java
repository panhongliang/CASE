package com.phl.jconsole.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2016-08-23.
 */
public class Thread {
    public static void createBusyThread(){
        java.lang.Thread thread=new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    ;
                }
            }
        }, "testbusyThread");
        thread.start();
    }

    public static void createLockThread(final Object lock){
        java.lang.Thread thread=new java.lang.Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, "testLockThread");
        thread.start();
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in)) ;
        br.readLine();
        createBusyThread();
        br.readLine();
        Object object=new Object();
        createLockThread(object);
    }
}
