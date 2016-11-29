package com.phl.arraytostring;

import java.util.Arrays;

/**
 * Created by panhongliang on 16/1/18.
 */
public class ArrayToString {

    public static void main(String[] args) {
        int a[]={1,2,3};
        int b[] = new int[3];
        b[0]=1;
        b[1]=2;
        b[2]=4;

        System.out.println(Arrays.toString(b));
    }
}
