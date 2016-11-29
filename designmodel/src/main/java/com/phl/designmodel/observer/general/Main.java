package com.phl.designmodel.observer.general;

/**
 * Created by panhongliang on 16/2/24.
 */
public class Main {
    public static void main(String[] args) {
        Obj o=new Obj();
        o.addListener(new PropertiesChangedListener() {
            public void onPropertiesChanged(PropertiesChangedEvent event) {
                System.out.println("event = " + event);
            }
        });
        o.setA(2);
        o.setA(5);
        o.setB("2");
    }
}
