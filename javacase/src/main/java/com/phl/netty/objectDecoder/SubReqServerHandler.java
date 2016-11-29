package com.phl.netty.objectDecoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by panhongliang on 16/1/25.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeRequest req=(SubscribeRequest)msg;
        if("PanHongliang".equalsIgnoreCase(req.getUserName())){
            System.out.println("service accept subscribe req msg = [" + msg + "]");
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }
    }
    private SubscribeResponse resp(int subReqId){
        SubscribeResponse response=new SubscribeResponse();
        response.setSubReqId(subReqId);
        response.setRespCode(0);
        response.setDesc("Netty book order successed,3 days later,sent to the designated address");
        return response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
