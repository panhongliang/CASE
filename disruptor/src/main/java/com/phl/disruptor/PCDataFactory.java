package com.phl.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * Created by Administrator on 2017-05-22.
 */
public class PCDataFactory implements EventFactory<PCData> {
    public PCData newInstance() {
        return new PCData();
    }
}
