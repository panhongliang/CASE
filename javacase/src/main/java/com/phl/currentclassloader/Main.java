package com.phl.currentclassloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.DriverManager;

/**
 * Created by Administrator on 2016-07-06.
 */
public class Main {
    /**
     * Thread.currentThread().getContextClassLoader()的意义:
     　　父Classloader可以使用当前线程Thread.currentthread().getContextLoader()中指定的classloader中加载的类。
     颠覆了父ClassLoader不能使用子Classloader或者是其它没有直接父子关系的Classloader中加载的类这种情况。
     这个就是Context Class Loader的意义
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("MainClassLoader:"+Main.class.getClassLoader());//sun.misc.Launcher$AppClassLoader
        System.out.println("ContextClassLoader:"+Thread.currentThread().getContextClassLoader());//sun.misc.Launcher$AppClassLoader
        System.out.println("systemclassloader:"+ClassLoader.getSystemClassLoader());
        new Thread(new Runnable() {
            public void run() {
                URL url[]=new URL[1];
                try {
                    url[0]=new URL("file:D:/sqljdbc.jar");
                    URLClassLoader myLoader=new URLClassLoader(url);
                    Class c=myLoader.loadClass("com.microsoft.sqlserver.jdbc.DTV");
                    System.out.println("Customize:"+c.getClassLoader());//java.net.URLClassLoader
                    Thread cu=Thread.currentThread();
                    ClassLoader lo= cu.getContextClassLoader();
                    System.out.println("ContextClassLoader:"+lo);//sun.misc.Launcher$AppClassLoader
                    Thread current=Thread.currentThread();
                    current.setContextClassLoader(c.getClassLoader());
                    System.out.println("---------setcontextclassloader------");
                    new Thread(new Runnable() {
                        public void run() {
                            Thread t=Thread.currentThread();
                            ClassLoader u=t.getContextClassLoader();
                            System.out.println("ContextClassLoader:"+u);//java.net.URLClassLoader
                    }
                    },"T2").start();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },"T1").start();
    }
}
