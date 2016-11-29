package com.phl.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panhongliang on 16/1/16.
 */
public class list {
    public static void main(String[] args) {
        List<String> list=new ArrayList<String>(){};
        for(int i=0;i<10;i++){
            list.add("aa"+i);
        }
      //  list.add(12,"cvb");
         list.subList(1,3).clear();
     /*   for(String s:list){
            list.remove(s);
        }*/
       for(int i=0,size=list.size();i<size;i++){
           System.out.println(list.get(i));
        }


    }
}
