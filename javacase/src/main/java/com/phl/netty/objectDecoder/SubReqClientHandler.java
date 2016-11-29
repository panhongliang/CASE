package com.phl.netty.objectDecoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by panhongliang on 16/1/25.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i=0;i<10;i++){
            ctx.write(req(i));
        }
        ctx.flush();
    }
    private SubscribeRequest req(int reqId){
        SubscribeRequest request=new SubscribeRequest();
        request.setAddress("zhejiang");
        request.setPhoneNumber("180xxxxxxxx");
        request.setProductName("Netty权威指南");
        request.setSubReqId(reqId);
        request.setUserName("PanHongliang");
        return request;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client receive response:["+msg+"]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.close();
    }
}
