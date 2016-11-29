package com.phl.PropertyChangeSupport;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Administrator on 2016-06-24.
 */
public class Main {
    public static void main(String[] args) {
        MyBean myBean=new MyBean();
        myBean.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {
                String newValue= (String) evt.getNewValue();
                String oldValue= (String) evt.getOldValue();
                String propName=evt.getPropertyName();
                System.out.println(String.format("newValue:%s ,oldValue %s,propName,%s",newValue,oldValue,propName));
            }
        });
        myBean.setValue("123");
        myBean.setValue("456");
    }
}
