package com.phl.hotloading;

import java.lang.reflect.Method;
import java.net.URL;

/**
 * Created by Administrator on --.
 */
public class TestHotClassLoader {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new MonitorHotSwap());
        t.start();
    }
}
class MonitorHotSwap implements Runnable {
     // Hot就是用于修改，用来测试热加载
     private String className = "com.phl.hotloading.TestClass";
     private Class hotClazz = null;
     private HotClassLoader hotloader =null;

             @Override
     public void run() {
         try {
             hotloader= HotClassLoader.getClassLoader();
                 while (true) {
                     hotClazz = hotloader.loadClass(className);
                     Object hot = hotClazz.newInstance();
                     Method m = hotClazz.getMethod("print");
                     m.invoke(hot, null); //打印出相关信息
                         // 每隔秒重新加载一次
                     Thread.sleep(5000);
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
     }
    
}
