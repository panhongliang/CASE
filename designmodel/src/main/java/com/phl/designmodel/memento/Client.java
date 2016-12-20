package com.phl.designmodel.memento;

/**
 * Created by Administrator on 2016-12-20.
 */
public class Client {
    public static void main(String[] args) {
        Originator originator=new Originator();
        originator.phrase1();//完成第一阶段
        Memento memento=originator.snapshot();//创建一个快照
        Caretaker caretaker=new Caretaker();
        caretaker.setMemento(memento);//管理者管理快照
        originator.phrase2_m1();//用第一种办法完成第二阶段
        originator.setSnapshot(memento);//从快照中恢复
        originator.phrase2_m2();//恢复之后用第二种方法完成第二阶段
    }
}
