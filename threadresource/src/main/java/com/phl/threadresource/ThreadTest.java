package com.phl.threadresource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by panhongliang on 16/1/14.
 */
public class ThreadTest {

    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                Thread.currentThread().setContextClassLoader(new MyClassLoader());
                ClassLoader loader=Thread.currentThread().getContextClassLoader();
                        System.out.println(Thread.currentThread().getName()+":"+loader);
               InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream("a.properties");
                Properties properties=new Properties();
                try {
                    properties.load(inputStream);
                    System.out.println("MyClassLoader:"+properties.get("b"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                ClassLoader loader=Thread.currentThread().getContextClassLoader();
                System.out.println(Thread.currentThread().getName()+":"+loader);
                InputStream inputStream= Thread.currentThread().getContextClassLoader().getResourceAsStream("a.properties");
                Properties properties=new Properties();
                try {
                    properties.load(inputStream);
                    System.out.println("CurrentThreadLoader:"+properties.get("b"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
