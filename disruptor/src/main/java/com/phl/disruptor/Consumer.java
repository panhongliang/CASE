package com.phl.disruptor;

import com.lmax.disruptor.WorkHandler;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017-05-22.
 */
public class Consumer implements WorkHandler<PCData> {

    public void onEvent(PCData event) throws Exception {
        sleep(1500);
        System.out.println("thread "+Thread.currentThread().getName()+"  consumer "+event.getValue());
    }
}
