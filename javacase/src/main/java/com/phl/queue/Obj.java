package com.phl.queue;

/**
 * @Title:Obj
 * @Description:
 * @Copyright:中国电信爱wifi运营中心
 * @author:panhl
 * @date 2017/9/28 0028 20:14
 */
public class Obj implements Comparable{
    private int a;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Obj(int a) {
        this.a = a;
    }

    @Override
    public int compareTo(Object o) {
        if(o==null){
            return 1;
        }
        if(o instanceof Obj){
            Obj other=(Obj) o;
            if(other.getA()==this.getA()){
                return 0;
            }
            return this.getA()>other.getA()?1:-1;
        }else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Obj{" +
                "a=" + a +
                '}';
    }
}
