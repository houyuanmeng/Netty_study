package com.hegang.firstDemo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

import static io.netty.buffer.Unpooled.copiedBuffer;

/**
 * Creat By ${侯某某} on 2019/11/21
 * 前面的处理器是netty自带的，此类我们来自己定义一个处理器
 */

public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject>{


    //此方法读取客户端发送过来的请求，并且给客户端返回响应的请求
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("执行了channelRead0");

    //流程进入到这个方法，说明请求已经过来了，我们要把请求构建出来，并给与响应
        if(msg instanceof HttpRequest) {
            HttpRequest httpRequest= (HttpRequest) msg;

            System.out.println(msg.getClass());
            System.out.println(ctx.channel().remoteAddress());

            //获取响应请求的方法名
            System.out.println("请求方法名："+httpRequest.method().name());

            URI uri = new URI(httpRequest.uri());
            if("favicon.ico".equals(uri.getPath())){
                System.out.println("请求favicon.ico");
                return;
            }


            ByteBuf content = copiedBuffer("hello netty", CharsetUtil.UTF_8);
            //构造响应,向客户端返回版本，状态，内容
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            //设置响应的头部响应内容的类型，和响应内容的长度
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            //将响应返回给客户端
            //需要写并且刷新
            ctx.writeAndFlush(response);

        }}

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重写方法channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重写方法channel Registered");
        super.channelRegistered(ctx);
    }



    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重写方法channel handlerAdded");
        super.handlerAdded(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重写方法channel  Inactive");
        super.channelInactive(ctx);
    }
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("重写方法channel channelUnregistered");
        super.channelUnregistered(ctx);
    }

}

