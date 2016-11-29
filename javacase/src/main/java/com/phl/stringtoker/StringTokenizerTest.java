package com.phl.stringtoker;

import java.util.StringTokenizer;

/**
 * Created by panhongliang on 16/1/17.
 */
public class StringTokenizerTest {
    public static void main(String[] args) {
        String str="abc,def,ghi,jkl";
        StringTokenizer tokenizer=new StringTokenizer(str,",");
        while (tokenizer.hasMoreTokens()) {
            System.out.println("tokenizer = " + tokenizer.nextToken());
        }
    }
}
