package com.phl.nio.filechannel;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by panhongliang on 16/3/31.
 */
public class PathTest {
    public static void main(String[] args)throws Exception{
        URI uri=new URI("file:/Users/panhongliang/error.txt");
        System.out.println("uri.getScheme() = " + uri.getScheme());
       /* System.out.println(FileSystems.getFileSystem(new URI("file:///Users/panhongliang/error.txt")));*/

        Path path3 = Paths.get(URI.create("file:///rafaelnadal/tournaments/2009/BNP.txt"));

        Path path4 = Paths.get(System.getProperty("user.home"));
        System.out.println(path4);

        System.out.println("aa:"+FileSystems.getDefault().getPath("file:///rafaelnadal/tournaments/2009/BNP.txt", "BNP.txt"));
        Path path= Paths.get("C:\\rafaelnadal\\tournaments\\2009");


        if(path.isAbsolute()){
            System.out.println("yes");
            System.out.println(path.getFileName());
            System.out.println(path.getFileSystem());

            System.out.println(path.getNameCount());

            System.out.println(path.getRoot());
            System.out.println(path.getParent());
            System.out.println(path.normalize().getParent());
        }
        byte b=47;
        System.out.println((char) b);

        Path path0 = Paths.get("/rafaelnadal/tournaments/2009");
        System.out.println(path0.toAbsolutePath());
        System.out.println("fileName:"+path0.getFileName());
    }
}
