package com.phl.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * @Title:SyncTest
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/18 0018 08:59
 */
public class SyncTest {
    public static void main(String[] args) {
        ExecutorService service= Executors.newFixedThreadPool(3);

        for(int i=0;i<4;i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                   Person p=new Person();
                   p.setName("123");
                    doRun(p);
                }
            });
        }
        service.shutdown();
    }
    private static void doRun(Person person){
        synchronized (person.getName().intern()){
            try {
                long s=System.currentTimeMillis();
                sleep(5000);
                long e=System.currentTimeMillis();
                System.out.println("use"+ (e-s)/1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class Person{
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
