package com.phl.designmodel.memento;

/**
 * Created by Administrator on 2016-12-20.
 */
public class Client {
    public static void main(String[] args) {
        Originator originator=new Originator();
        originator.phrase1();
        Memento memento=originator.snapshot();
        Caretaker caretaker=new Caretaker();
        caretaker.setMemento(memento);
        originator.phrase2_m1();
        originator.setSnapshot(memento);
        originator.phrase2_m2();
    }
}
