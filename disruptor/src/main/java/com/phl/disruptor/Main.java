package com.phl.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * Created by Administrator on 2017-05-22.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        produceAndConsume();
    }


    public static void create(final RingBuffer<PCData> ringBuffer,final AtomicInteger data) throws InterruptedException {
        Producer producer=new Producer(ringBuffer);
        ByteBuffer bb=ByteBuffer.allocate(8);
        for(long l=0;true;l++){
            bb.putLong(0,data.incrementAndGet());
            producer.pushData(bb);
            sleep(500);

        }
    }

    public static void produceAndConsume(){
        PCDataFactory factory=new PCDataFactory();
        int bufferSize=128;
        final AtomicInteger integer=new AtomicInteger();
        final AtomicInteger data=new AtomicInteger();
        Disruptor<PCData> disruptor=new Disruptor<PCData>(
                factory,
                bufferSize,
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("Disruptor_Thread"+integer.incrementAndGet());
                        return t;
                    }
                }
        );
        disruptor.handleEventsWithWorkerPool(new Consumer()
                //   new Consumer(),
                //  new Consumer(),
                //  new Consumer(),
                // new Consumer()
        );
        disruptor.start();
        final RingBuffer<PCData> ringBuffer=disruptor.getRingBuffer();

        final ExecutorService executor=Executors.newFixedThreadPool(4);
        for(int i=0;i<5;i++){
            executor.submit(new Runnable() {
                public void run() {
                    try {
                        create(ringBuffer,data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }
}
