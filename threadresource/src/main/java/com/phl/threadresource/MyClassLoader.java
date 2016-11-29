package com.phl.threadresource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/*
  Created by panhongliang on 16/1/14.
 */
public class MyClassLoader extends ClassLoader {


    public Class findClass(String name) {
             byte[] b = loadClassData(name);
             return defineClass(name, b, 0, b.length);
     }

    private byte[] loadClassData(String name) {
        try {
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            FileInputStream fileInputStream=new FileInputStream(
                    new File("/Users/panhongliang/360云盘/test/threadresource/src/main/java/com/phl/threadresource/AClass.java"));
            FileChannel channel=fileInputStream.getChannel();
            while (channel.read(byteBuffer)>0) {
                byteBuffer.flip();
                bos.write(byteBuffer.array(),0,byteBuffer.limit());
                byteBuffer.clear();
            }
            channel.close();
            fileInputStream.close();
            return bos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    
}
