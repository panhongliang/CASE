package com.phl.netty.delimiterBasedFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by panhongliang on 16/3/11.
 */
public class EchoServerHandler extends ChannelHandlerAdapter {

    int counter=0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body=(String) msg;
        System.out.println(" this is "+ ++counter+"times receiver clinet:["+body+"]" );
        ByteBuf echo= Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(echo);
    }
}
