package com.hegang.secondDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * Creat By ${侯某某} on 2019/11/21
 */

//客户端代码实现
public class myClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new myClientInitializer());

            ChannelFuture channelFuture=bootstrap.connect("localhost",8899).sync();
             channelFuture.channel().closeFuture().sync();

        }finally {
            eventLoopGroup.shutdownGracefully();
        }


    }
}
