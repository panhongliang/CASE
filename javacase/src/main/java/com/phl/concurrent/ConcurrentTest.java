package com.phl.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by panhongliang on 15/11/29.
 */
public class ConcurrentTest {
    public static void main(String[] args) throws  Exception{
       /* final ConcurrentHashMap map=new ConcurrentHashMap();

        Thread t1= new Thread(new Runnable() {
            public void run() {
                map.put(Thread.currentThread().getName()+":k",Thread.currentThread().getName()+":v");
            }
        });

        Thread t2= new Thread(new Runnable() {
            public void run() {
                map.put(Thread.currentThread().getName()+":k",Thread.currentThread().getName()+":v");
            }
        });
        t1.start();t2.start();t1.join();t2.join();
       int i=1;*/

    /*    int a[]=new int[2];
        a[0]=1;
        int b[]={};
        System.out.println(a.length);*/

/*        Map<String,String> props=new HashMap<String, String>();
        props.put("ka","va");
        props.put("kb","vb");
        Set<Map.Entry<String, String>> set= props.entrySet();
        Iterator iter= set.iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = props.get(key);
        }*/
        System.out.println(System.nanoTime() + "");
        LockSupport.parkNanos(1000000000);
        System.out.println(System.nanoTime()+"");
        LockSupport.unpark(Thread.currentThread());
        LockSupport.unpark(Thread.currentThread());
        LockSupport.parkNanos(1000000000);
        System.out.println(System.nanoTime()+"");
    }



}
