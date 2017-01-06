package com.phl.serial;

import java.io.Serializable;

/**
 * Created by Administrator on 2016-12-23.
 */
public class Obj implements Serializable{
    int a;
    String b;
    Obj o;

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

    public Obj getO() {
        return o;
    }

    public void setO(Obj o) {
        this.o = o;
    }
}
