package com.phl.xml.digester;

/**
 * Created by Administrator on 2016-06-23.
 */
public class Outer {

    String name;
    int id;
    Inner inner;

    public Outer() {
    }

    public Outer(String name, int id, Inner inner) {
        this.name = name;
        this.id = id;
        this.inner = inner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inner getInner() {
        return inner;
    }

    public void setInner(Inner inner) {
        this.inner = inner;
    }
}
