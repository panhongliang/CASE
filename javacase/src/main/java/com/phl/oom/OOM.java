package com.phl.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panhongliang on 15/10/24.
 */
public class OOM {

    public static void main(String[] args) {
        Map<String, O> map=new HashMap<String, O>();
        for (int i=0;i<1000000000;i++){
            O o=new O();
            System.out.printf(i+"\n");
            map.put(i + "", o);
        }
    }
}
class O{
    int age;
    String name;
    public O(){
        this.age=10;
        this.name=Math.random()+"";
    }
}
