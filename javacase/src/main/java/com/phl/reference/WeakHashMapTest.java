package com.phl.reference;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2016-11-03.
 */
public class WeakHashMapTest {

    public static void testWeakHashMap(){
        int size = 1000;
        Key[] keys = new Key[size];
        WeakHashMap<Key,Value> whm = new WeakHashMap<Key,Value>();
        int j=0;
        for(int i=0;i<size;i++) {
            Key k =null;
            k=new Key(Integer.toString(i));
            if(i%5==0){
                //能被5整除的key都被keys强引用，不会被gc
                keys[j++]=k;
            }
            Value v = new Value(Integer.toString(i));
            whm.put(k,v);
        }
        System.gc();
        try {
            Thread.sleep(20000);  //把处理器的时间让给垃圾回收器进行垃圾回收
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        testWeakHashMap();
    }
}


class Key {
    String id;

    public Key(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object r) {
        return (r instanceof Key)
                && id.equals(((Key)r).id );
    }

    public void finalize() {
        //打印出来的id不会含有5的倍数的数
        System.out.println("Finalizing Key "+id);
    }
}

class Value {
    String id;

    public Value(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }

    public void finalize(){
        System.out.println("Finalizing Value "+id);
    }

}
