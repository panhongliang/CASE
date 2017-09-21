package com.phl.threadpool;

import com.phl.zk.Executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Title:ScheduledExecutorMain
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/20 0020 10:20
 */
public class ScheduledExecutorMain {
    public static void main(String[] args) {
        ScheduledExecutorService service=Executors.newScheduledThreadPool(1);
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("=========");
            }
        },1, TimeUnit.MINUTES);
        service.shutdown();
    }
}
