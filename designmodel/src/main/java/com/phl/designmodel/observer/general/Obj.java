package com.phl.designmodel.observer.general;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by panhongliang on 16/2/24.
 */
public class Obj {
    private Vector<PropertiesChangedListener> listeners=new Vector<PropertiesChangedListener>();

    int a;
    String b;

    public String getB() {
        return b;
    }

    public void setB(String newB) {
        String old=this.b;
        this.b=newB;
        this.fire(buildEvent(old, newB));
    }

    public int getA() {
        return a;
    }

    public void setA(int newA) {
        int old=this.a;
        this.a = newA;
        this.fire(buildEvent(old, newA));
    }
    public void addListener(PropertiesChangedListener listener){
        listeners.add(listener);
    }

    public void remvoeLister(PropertiesChangedListener listener){
        listeners.remove(listener);
    }

    public void fire(PropertiesChangedEvent event){
        Iterator iterator= listeners.iterator();
        while (iterator.hasNext()){
            PropertiesChangedListener listener=(PropertiesChangedListener) iterator.next();
            listener.onPropertiesChanged(event);
        }
    }
    private <T> PropertiesChangedEvent  buildEvent(T old,T newV){
        PropertiesChangedEvent<T> event=new PropertiesChangedEvent<T>(this);
        String mthName=Thread.currentThread().getStackTrace()[2].getMethodName();
        event.setFiledName(mthName.substring(3));
        event.setNewValue(newV);
        event.setOldValue(old);
        return event;
    }


}
