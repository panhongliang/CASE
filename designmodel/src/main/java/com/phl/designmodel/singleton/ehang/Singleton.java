package com.phl.designmodel.singleton.ehang;

/**
 * Created by panhongliang on 16/1/11.
 */
public class Singleton {
    /**
     * 在类加载的时候就实现单例
     * 如果该类被多次加载照样会多次被实例化
     */
    private static Singleton instance=new Singleton();

    private Singleton(){}

    /**
     *
     * @return
     */
    public static Singleton getInstance(){
        return instance;
    }
}
