package com.phl.designmodel.memento;

/**
 * Created by Administrator on 2016-12-20.
 * 备忘录的发起人，需要保存中间状态，然后在某一时间恢复这个状态
 */
public class Originator {
    private String state;
    public void phrase1(){
        state="abc";
    }
    public void phrase2_m1(){
        if("abc".equals(state)){
            state+="phrase2";
            System.out.println("ABC:"+state);
        }else {
            System.out.println("m1 unknow");
        }
    }
    public void phrase2_m2(){
        if("abc".equals(state)){
            state+="phrase2";
            System.out.println("ABC:"+state);
        }else {
            System.out.println("m2 unknow");
        }
    }
    public Memento snapshot(){
        return new MementoImpl(state);
    }
    public void setSnapshot(Memento memento){
        MementoImpl impl=(MementoImpl)memento;
        this.state=impl.getState();
    }

    private class MementoImpl implements Memento{
        private String state;
        public MementoImpl(String state){
            this.state=state;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
