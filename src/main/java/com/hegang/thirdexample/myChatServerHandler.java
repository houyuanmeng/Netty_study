package com.hegang.thirdexample;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.ChannelMatcher;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Collection;
import java.util.Iterator;

/**
 * Creat By ${侯某某} on 2019/11/22
 */
public class myChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 定义一个channelGroup，用来保存多个用户
     * 一个用户表示一个channel，将他们加入到一个组
     */
    private static ChannelGroup channelGroup=new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel= ctx.channel();
        // channelGroup.forEach用于取出每一个对象
        channelGroup.forEach(ch ->{
            if (channel !=ch){
                ch.writeAndFlush("客户"+channel.remoteAddress()+"发送消息："+msg+"\n");
            }else {
                ch.writeAndFlush("[自己发送的消息]"+msg+"\n");
            }
        } );
    }

    @Override  //表示链接建立 一旦连接，第一个执行
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel=ctx.channel();
        /**
         * channelGroup的writeAndFlush有点特别，他将循环对里面每一个channel进行输出
         * 如：假如A上线，会通知channelGroup其他channel，但是不会通知A，因为此时没有加入A
         * 如果也想通知自己，那么在输出前将自己加入channelGroup就好（注意他们的顺序）
         */
       channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress() + "加入\n");
        channelGroup.add(channel);
    }

    @Override// 断开连接时执行
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]"+channel.remoteAddress() + "离开\n");
       
    }

    @Override// 表示连接处于活动状态
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress()+"下线");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
