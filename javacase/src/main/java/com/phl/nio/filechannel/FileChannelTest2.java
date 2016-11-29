package com.phl.nio.filechannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by panhongliang on 16/3/31.
 */
public class FileChannelTest2 {
    public static void main(String[] args) {
        copy(new File("/Users/panhongliang/error.txt"),new File("/Users/panhongliang/zookeeper-3.4.6_s1/error.txt"));
    }

    private static void copy(File from, File to) {

        FileInputStream in=null;
        FileOutputStream out=null;
        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try {
             in=new FileInputStream(from);
             out=new FileOutputStream(to);
             inChannel=in.getChannel();
             outChannel=out.getChannel();

            ByteBuffer buf=ByteBuffer.allocate(1024);
            int readSize=0;
            while ((readSize=inChannel.read(buf))!=-1){
                buf.flip();
                outChannel.write(buf);
                buf.clear();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
             try {
                 if(in!=null)
                    in.close();
                 if(out!=null)
                     out.close();
                 if(inChannel!=null)
                     inChannel.close();
                 if(outChannel!=null)
                     outChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
