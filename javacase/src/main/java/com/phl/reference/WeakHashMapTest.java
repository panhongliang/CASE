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
        Key key4=null;
        for(int i=0;i<size;i++) {
            Key k =null;
            if(i==4){
                key4=new Key(Integer.toString(i));
                k=key4;
            }
            k = new Key(Integer.toString(i));
            Value v = new Value(Integer.toString(i));
            whm.put(k,v);
        }

        System.gc();

        try {
            Thread.sleep(8000);  //把处理器的时间让给垃圾回收器进行垃圾回收
            System.out.println(whm.get(key4));
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
