package com.phl.netty.tcpAttachPackage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by panhongliang on 16/1/21.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {



    private int counter;

    private byte[] req;
    public TimeClientHandler(){
        req=("QUERY TIME ORDER"+System.getProperty("line.separator")).getBytes();

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ByteBuf firstMessage;
        for(int i=0;i<100;i++){
            firstMessage= Unpooled.buffer(req.length);
            firstMessage.writeBytes(req);
            ctx.writeAndFlush(firstMessage);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    // the channelActive() method will be invoked when a connection is established and ready to generate traffic
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf=(ByteBuf)msg;
        byte [] req=new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body=new String(req,"UTF-8");
        System.out.printf("Now is:"+body+";the counter is :"+ ++counter);
        ctx.close();
    }
}
