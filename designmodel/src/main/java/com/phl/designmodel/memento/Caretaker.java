package com.phl.designmodel.memento;

/**
 * Created by Administrator on 2016-12-20.
 * 备忘录的管理者
 */
public class Caretaker {
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}
