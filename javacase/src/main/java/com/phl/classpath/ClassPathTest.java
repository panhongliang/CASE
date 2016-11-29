package com.phl.classpath;

import sun.misc.Launcher;
import sun.misc.URLClassPath;

import java.net.URL;

/**
 * Created by panhongliang on 16/1/17.
 */
public class ClassPathTest {
    public static void main(String[] args) {
        String paths[]=System.getProperty("java.class.path").split(":");
        System.out.println("classpath :");
        for(int i=0;i<paths.length;i++){
            System.out.println(paths[i]);
        }

        URLClassPath bootstrapClassPath= Launcher.getBootstrapClassPath();
        URL bootUrls[]=bootstrapClassPath.getURLs();
        System.out.println("bootstrapClassPath :");
        for(URL l:bootUrls){
            System.out.println(l.getPath());
        }

        System.out.println("java.ext.dirs :");

        String extpaths[]=System.getProperty("java.ext.dirs").split(":");

        for(int i=0;i<extpaths.length;i++){
            System.out.println(extpaths[i]);
        }
    }
}
