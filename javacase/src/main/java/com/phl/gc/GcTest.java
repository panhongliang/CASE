package com.phl.gc;

/**
 * Created by panhongliang on 16/4/11.
 */
public class GcTest {
    static int m = 1024 * 1024;

    public static void main(String[] args) {
        //分配2兆
        byte[] a1 = new byte[2 * m];
        System.out.println("a1 ok");
        //分配2兆
        byte[] a2 = new byte[2 * m];
        System.out.println("a2 ok");
    }

}
