package com.phl.executors;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2016-11-03.
 */
public class TestScheduledExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(2);
        final Int rate=new Int(0);
        final Int delay=new Int(0);
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                 //   int now =new Date().getSeconds();
                    System.out.println("rate:");
                  //  sleep(3000);
                  //  rate.setI(now);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },2,3, TimeUnit.SECONDS);

        /*
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    int now =new Date().getSeconds();
                    System.out.println("delay:"+(now-delay.getI()));
                    sleep(3000);
                    delay.setI(now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },2,2, TimeUnit.SECONDS);*/
    }

}
