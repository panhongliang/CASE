package com.phl.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;

/**
 * @Title:Main
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/10/10 0010 11:18
 */
public class Main {
    public static void main(String[] args) {
        //t1();
           // t2();
        //t3();
        //t4();
        initMap();
    }
    public static void initMap(){
        ConcurrentHashMap map=new ConcurrentHashMap(3);
        map.put("a","a");
        map.put(new Integer(1),"d");

    }

    public static void t4(){
        ConcurrentHashMap map=new ConcurrentHashMap();
        O o=new O();
        int i=0;
        for(i=0;i<100;i++){
            map.put(i+"",new O());
        }
        //map.put("456",o);

        map.remove("123");

        System.out.println(map.get("456"));
    }
    public static void t3(){
        ConcurrentHashMap map=new ConcurrentHashMap();
        O o=new O();
        map.put("123",o);
        o=null;
        O o2= (O) map.get("123");
        System.out.println(o2);
    }
    public static void t1(){
        final ConcurrentHashMap hashMap=new ConcurrentHashMap();
        ExecutorService service=Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    hashMap.putIfAbsent("123",new O());
                }
            });
        }
        service.shutdown();
        while (!service.isTerminated()){}

        System.out.println(hashMap.size());
    }
    public static void t2(){
        final ConcurrentHashMap hashMap=new ConcurrentHashMap();
        ExecutorService service=Executors.newFixedThreadPool(3);
        for(int i=0;i<3;i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    hashMap.computeIfAbsent("123",new Function(){

                        @Override
                        public Object apply(Object o) {
                            return new O();
                        }
                    });
                }
            });
        }
        service.shutdown();
        while (!service.isTerminated()){}

        System.out.println(hashMap.size());
    }
   static class O{
        public O(){
            System.out.println("init");
        }
    }
}
