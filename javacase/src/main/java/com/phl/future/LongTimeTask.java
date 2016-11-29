package com.phl.future;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by panhongliang on 15/11/2.
 */
public class LongTimeTask {
    public int doTask(TaskId taskId){
        try {
            float aFloat=ThreadLocalRandom.current().nextFloat();
            int a= (int) (2*aFloat);
            Thread.sleep(a*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return taskId.getId();
    }
}
