package com.phl.serial;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by Administrator on 2016-12-23.
 */
public class Obj   implements Serializable{
    int a;
    String b="12a我";
    Test t;
    HashMap<String,String> map;

    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public Test getT() {
        return t;
    }

    public void setT(Test t) {
        this.t = t;
    }
}
