package com.phl.io.copy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016-12-13.
 */
public class Copy {
    public static void main(String[] args) throws Exception{
        Copy.streamCopy("D:\\case_test\\from.txt","D:\\case_test\\to.txt");
        Copy.channelCopy("D:\\case_test\\from1.txt","D:\\case_test\\to1.txt");
        Copy.nioBufferCopy("D:\\case_test\\from2.txt","D:\\case_test\\to2.txt");
    }
    public  static void streamCopy(String src, String  dest)throws Exception{
        File from=new File(src);
        FileInputStream inputStream=new FileInputStream(from);
        FileOutputStream outputStream=new FileOutputStream(dest);
        byte temp[]=new byte[1024];
        int bytes=-1;
        while ((bytes=inputStream.read(temp))!=-1){
            outputStream.write(temp,0,bytes);
        }
        inputStream.close();
        outputStream.close();
    }
    public static void channelCopy(String src,String dest) throws Exception{
        File from=new File(src);
        FileInputStream inputStream=new FileInputStream(from);
        FileOutputStream outputStream=new FileOutputStream(dest);
        FileChannel in=inputStream.getChannel();
        FileChannel out=outputStream.getChannel();
        in.transferTo(0,in.size(),out);
        in.close();
        out.close();
        inputStream.close();
        outputStream.close();
    }
    public static void nioBufferCopy(String src,String dest) throws Exception{
        File from=new File(src);
        FileInputStream inputStream=new FileInputStream(from);
        FileOutputStream outputStream=new FileOutputStream(dest);
        FileChannel in=inputStream.getChannel();
        FileChannel out=outputStream.getChannel();
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while (in.read(byteBuffer)!=-1){
            byteBuffer.flip();
            out.write(byteBuffer);
            byteBuffer.clear();
        }
        inputStream.close();
        outputStream.close();
        in.close();
        out.close();
    }
}
