package com.phl.hashcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by panhongliang on 16/1/18.
 */
public class HashCodeTest {
    public static void main(String[] args) {
        A a=new A();
        A b=new A();
        int c=0;
        Map map=new HashMap<A,String>();
        map.put(a,"a");
        System.out.println(map.get(b));
    }
}
class  A{
    int a=0;

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        A a1 = (A) o;

        return a == a1.a;

    }
}