package com.phl.gc;

import com.phl.threadLocalAndThreadPool.T;

/**
 * Created by Administrator on 2016-08-23.
 * VM：  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:PretenureSizeThreshold=3145728B
 * PretenureSizeThreshold 不能直接写3M
 * PretenureSizeThreshold 只对Serial 和ParNew两款收集器有效
 * 令大于这个设置值的对象直接在老年代中分配，
 * 这样做的目的是避免在Eden区及两个Survivor区之间发生大量的内存拷贝（复制算法收集回收内存）
 */
public class TestPretenureSizeThreshold {

    private static final int _1MB=1024*1024;
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation=new byte[4*_1MB];
    }

    public static void main(String[] args) {
        TestPretenureSizeThreshold.testPretenureSizeThreshold();
    }
}
