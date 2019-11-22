package com.hegang.secondDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Creat By ${侯某某} on 2019/11/22
 */
public class myClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    //服务器向客户端发送消息的时候，就会被调用
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output"+msg);
        ctx.writeAndFlush("from Client"+ LocalDateTime.now());
    }
   //回调函数，用于触发客户端和服务器的链接
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
       ctx.writeAndFlush("来自客户端的问候");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
