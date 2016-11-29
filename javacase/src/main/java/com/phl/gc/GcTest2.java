package com.phl.gc;

/**
 * Created by Administrator on 2016-08-23.
 * VM：  -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:+PrintGCDetails
 * eden :8192k =8M
 * from :1024k
 * to :1024k
 * old :10240
 */
public class GcTest2 {
    private static final int _1MB=1024*1024;
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[2*_1MB];
        allocation2=new byte[2*_1MB];
        allocation3=new byte[2*_1MB];
        allocation4=new byte[4*_1MB];
    }

    public static void main(String[] args) {
        GcTest2.testAllocation();
    }
}
