package com.phl.designmodel.observer.general;

import java.util.EventObject;

/**
 * Created by panhongliang on 16/2/24.
 */
public class PropertiesChangedEvent<T> extends EventObject{

    private String filedName;
    private T oldValue;
    private T newValue;

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public T getNewValue() {
        return newValue;
    }

    public void setNewValue(T newValue) {
        this.newValue = newValue;
    }

    public T getOldValue() {
        return oldValue;
    }

    public void setOldValue(T oldValue) {
        this.oldValue = oldValue;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public PropertiesChangedEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "PropertiesChangedEvent{" +
                "filedName='" + filedName + '\'' +
                ", source=" + source +
                ", oldValue=" + oldValue +
                ", newValue=" + newValue +
                '}';
    }
}
