package com.phl.disruptor;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2017-05-22.
 */
public class Producer {
    private final RingBuffer<PCData> ringBuffer;
    public Producer(RingBuffer<PCData> ringBuffer){
        this.ringBuffer=ringBuffer;
    }
    public void pushData(ByteBuffer bb){
        long sequence=ringBuffer.next();
        try {
            PCData event=ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        }
        finally {
            ringBuffer.publish(sequence);
            System.out.println("producer  data "+bb.getLong(0));
        }
    }
}

