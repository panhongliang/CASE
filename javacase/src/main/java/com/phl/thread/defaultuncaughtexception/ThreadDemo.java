package com.phl.thread.defaultuncaughtexception;

/**
 * Created by Administrator on 2017-02-10.
 */
public class ThreadDemo {
    public static void main(String[] args) {

        Thread t = new Thread(new AdminThread());


        t.setDefaultUncaughtExceptionHandler(new Thread.
                UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t + " throws exception: " + e);
            }
        });
        // this will call run() function
        t.start();
    }
}
