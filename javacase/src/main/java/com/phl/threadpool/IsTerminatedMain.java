package com.phl.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

/**
 * @Title:IsTerminatedMain
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/28 0028 15:17
 */
public class IsTerminatedMain {
    public static void main(String[] args) {
        new IsTerminatedMain().isTermainated();
    }
    private void isTermainated(){
        ExecutorService service= Executors.newFixedThreadPool(1);
        service.submit(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("======i am alive");
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        service.shutdown();
        while (true){
            System.out.println(service.isTerminated());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
