package com.phl.designmodel.singleton.singletonpromote;

import java.util.HashMap;

/**
 * Created by panhongliang on 16/1/11.
 */
public class Singleton {

    private static HashMap<String,Singleton> registry=new HashMap();

    static private Singleton s=new Singleton();

    protected Singleton(){}

    public static Singleton getInstance(String name){
        if(name==null) name="Singleton";

        if(registry.get(name)==null){
            try {
                registry.put(name,(Singleton)Class.forName(name).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return registry.get(name);
    }
}
