package com.phl.useresources;


import com.phl.test1.R;
import sun.misc.Launcher;
import sun.net.www.ParseUtil;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by panhongliang on 16/1/11.
 */
public class UseResource {
   static class MyClassLoader extends URLClassLoader{
        public MyClassLoader(URL[] urls, ClassLoader parent){
            super(urls, parent);
        }
    }
    public static void main(String[] args) throws Exception{

       // R r=new R();
       // System.out.printf("r.b:"+r.getB());

        final String s = System.getProperty("java.class.path");
        System.out.println(s);
        System.out.println();
        String s1 = System.getProperty("java.ext.dirs");
        System.out.println(s1);

        String bootClassPath =System.getProperty("sun.boot.class.path");
        System.out.println(bootClassPath);
        final File[] path =  new File[0]  ;
        URL[] rul=pathToURLs(path);

        MyClassLoader myClassLoader=new UseResource.MyClassLoader(rul , null);
        int a=0;
    }


    private static URL[] pathToURLs(File[] path) {
        URL[] urls = new URL[path.length];
        for (int i = 0; i < path.length; i++) {
            urls[i] = getFileURL(path[i]);
        }
        // DEBUG
        //for (int i = 0; i < urls.length; i++) {
        //  System.out.println("urls[" + i + "] = " + '"' + urls[i] + '"');
        //}
        return urls;
    }
    static URL getFileURL(File file) {
        try {
            file = file.getCanonicalFile();
        } catch (IOException e) {}

        try {
            return ParseUtil.fileToEncodedURL(file);
        } catch (MalformedURLException e) {
            // Should never happen since we specify the protocol...
            throw new InternalError();
        }
    }
}
