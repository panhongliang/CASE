package com.phl.designmodel.singleton.langhang;

/**
 * Created by panhongliang on 16/1/11.
 */
public class Singleton {

    private static Singleton instance=null;

    private Singleton(){};

    /**
     * 注意：synchronized 防止多多线程产生多个实例，
     * 用了同步，速度慢
     * @return
     */
    public static synchronized Singleton getInstance(){
        if(instance!=null){
            return instance;
        }
        return new Singleton();
    }
}
