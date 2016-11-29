package com.phl.hotloading;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016-09-09.
 */
public class HotClassLoader extends URLClassLoader {

    private static Map<String,Long> lastModifyTime=new HashMap<>();

    private static String projectClassPath = "D:\\360CloudUI\\Cache\\1347785479\\test\\javacase\\target\\classes\\";
    private static HotClassLoader instance=new HotClassLoader();
    public static HotClassLoader  getClassLoader(){
        return instance;
     }
    public HotClassLoader() {
        super(getMyURLs());
    }
    private static  URL[] getMyURLs(){
        URL url = null;
        try {
            url = new File(projectClassPath).toURI().toURL();
        } catch (MalformedURLException e) {
                         e.printStackTrace();
        }
        return new URL[] { url };
    }
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class clazz=findLoadedClass(name);
        if(clazz!=null){
            if(resolve){
                resolveClass(clazz);
            }
            if(isModify(name)){
                clazz = customLoad(name,new HotClassLoader());
            }
            return  clazz;
        }
        if(name.startsWith("java.")){
             try {
                     //得到系统默认的加载cl，即AppClassLoader
                     ClassLoader system = ClassLoader.getSystemClassLoader();
                     clazz = system.loadClass(name);
                     if (clazz != null) {
                             if (resolve)
                                     resolveClass(clazz);
                             return (clazz);
                         }
                 } catch (ClassNotFoundException e) {
                     // Ignore
                 }
         }
        return customLoad(name,this);

    }
    public Class customLoad(String name,ClassLoader loader) throws ClassNotFoundException {
        return customLoad(name, loader,false);
    }
    public Class customLoad(String name, ClassLoader loader,boolean resolve)throws ClassNotFoundException {
        HotClassLoader hotLoader= (HotClassLoader) loader;
        Class clazz = hotLoader.findClass(name);
        if (resolve)
            hotLoader.resolveClass(clazz);
        long lastModify = getClassLastModifyTime(name);
        lastModifyTime.put(name,Long.valueOf(lastModify));
        return clazz;
     }
    private boolean isModify(String name){
        long lastmodify = getClassLastModifyTime(name);
        Long previousModifyTime = lastModifyTime.get(name);
        if(previousModifyTime==null)return false;
        if(lastmodify>previousModifyTime){
            return true;
        }
        return false;
    }
    private long getClassLastModifyTime(String name){
        String p=(projectClassPath+name).replaceAll("\\.","\\\\");
        File file = new File(p+".class");
        if(!file.exists()){
            throw new RuntimeException(new FileNotFoundException(name));
        }
        return file.lastModified();
    }
}
