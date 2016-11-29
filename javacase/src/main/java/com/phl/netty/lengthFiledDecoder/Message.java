package com.phl.netty.lengthFiledDecoder;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by panhongliang on 16/3/11.
 */
public class Message {
    /**
     * <LengthFiled><Data>
     */
    int lengthFiledValue;
    int lengthFiledLength1=4;
    Object data2;

    public Message(Object data2){
        this.data2=data2;
        lengthFiledValue=data2.toString().getBytes().length;
    }
    public ByteBuffer decode(){
        ByteBuffer data=ByteBuffer.allocate(lengthFiledLength1+lengthFiledValue);
        data.putInt(lengthFiledValue);
        data.put(data2.toString().getBytes());
        return data;
    }
    public String encode(ByteBuffer bytes){
        int length=bytes.limit();
        int dataLength=bytes.getInt();
        byte[] data=new byte[dataLength];
        bytes.get(data);
        final String d = new String(data, Charset.forName("UTF-8"));
        return d;
    }

}
