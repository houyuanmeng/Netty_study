package com.hegang.thirdexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.time.LocalDateTime;

/**
 * Creat By ${侯某某} on 2019/11/22
 */
public class myChatClientHandler extends SimpleChannelInboundHandler<String> {

        @Override
        //服务器向客户端发送消息的时候，就会被调用
        protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

            System.out.println("client output"+msg);
            System.out.println("mmmmm");

        }
    }

