package com.phl.nio.filechannel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by panhongliang on 16/1/26.
 */
public class FileChannelTest {
    public void read(String path) throws Exception {
        RandomAccessFile aFile=new RandomAccessFile(path,"rw");
        FileChannel inChannel=aFile.getChannel();

        ByteBuffer buf=ByteBuffer.allocate(48);

        int bytesRead=inChannel.read(buf);

        while (bytesRead!=-1){
            System.out.println("bytesRead = [" + bytesRead + "]");
            buf.flip();
            while (buf.hasRemaining()){
                System.out.println((char)buf.get());
            }
            buf.clear();
            bytesRead=inChannel.read(buf);
        }

        aFile.close();
    }
}
