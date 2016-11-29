package com.phl.list;

/**
 * Created by panhongliang on 16/1/16.
 */
public class Test {
    public static void main(String[] args) {
        MyList<String> myList=new MyList<String>();
        myList.add("aa");
        myList.add("bb");
        myList.add("cc");
        myList.add("dd");
        myList.add("ee");
        myList.add("ff");
        myList.removeRange(1,3);
        for(String s:myList){
            System.out.println(s);
        }
    }
}
