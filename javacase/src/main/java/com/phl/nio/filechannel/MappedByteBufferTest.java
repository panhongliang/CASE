package com.phl.nio.filechannel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by panhongliang on 16/3/31.
 */
public class MappedByteBufferTest {
    public static void main(String[] args) {
        copy2(new File("/Users/panhongliang/error.txt"), new File("/Users/panhongliang/zookeeper-3.4.6_s1/error1.txt"));
    }

    private static void copy(File from, File to) {

        RandomAccessFile in=null;
        RandomAccessFile out=null;

        FileChannel inFileChannel=null;
        FileChannel outFileChannel=null;
        try {
             in=new RandomAccessFile(from,"r");
             out=new RandomAccessFile(to,"rw");

             inFileChannel=in.getChannel();
             outFileChannel=out.getChannel();
            MappedByteBuffer mappedByteBuffer=inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inFileChannel.size());
            //mappedByteBuffer.flip();
            outFileChannel.write(mappedByteBuffer);
        } catch( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null) in.close();
                if(out!=null) out.close();
                if(inFileChannel!=null) inFileChannel.close();
                if(outFileChannel!=null) outFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void copy2(File from, File to) {

        RandomAccessFile in=null;
        RandomAccessFile out=null;

        FileChannel inFileChannel=null;
        FileChannel outFileChannel=null;
        try {
            in=new RandomAccessFile(from,"r");
            out=new RandomAccessFile(to,"rw");

            inFileChannel=in.getChannel();
            outFileChannel=out.getChannel();
            long size=inFileChannel.size();

            MappedByteBuffer inMap=inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);
            MappedByteBuffer outMap=outFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, size);
            outMap.put(inMap);
        } catch( Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(in!=null) in.close();
                if(out!=null) out.close();
                if(inFileChannel!=null) inFileChannel.close();
                if(outFileChannel!=null) outFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
