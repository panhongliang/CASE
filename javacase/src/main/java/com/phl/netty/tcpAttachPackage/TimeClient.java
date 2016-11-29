package com.phl.netty.tcpAttachPackage;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Created by panhongliang on 16/1/21.
 */
public class TimeClient {

    public void run() {
        //it will be used both as a boss group and as a worker group. The boss worker is not used for the client side though.
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            //it's for non-server channels such as a client-side or connectionless channel.
            Bootstrap b=new Bootstrap();
            b.group(workerGroup);
            //NioSocketChannel is being used to create a client-side Channel.
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new TimeClientHandler());
                }
            });

            ChannelFuture f=b.connect("localhost", 8080).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        TimeClient client=new TimeClient();
        client.run();
    }
}
