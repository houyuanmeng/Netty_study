package com.hegang.secondDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * Creat By ${侯某某} on 2019/11/21
 */

//要继承一个简化的类
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //先将远程客户端的地址打印出来 顺便将客户端向服务器发送的内容也打印出来
        System.out.println(ctx.channel().remoteAddress()+","+msg);
        //服务器收到消息后 给客户端一个反馈
          //写并且缓冲
        ctx.channel().writeAndFlush("from server"+ UUID.randomUUID());
    }

    //异常类
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      cause.printStackTrace();
      ctx.close();
    }
}
