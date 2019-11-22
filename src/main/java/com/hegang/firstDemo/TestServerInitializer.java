package com.hegang.firstDemo;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Creat By ${侯某某} on 2019/11/21
 */

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();//获取链接通道上面的管道，在上面加拦截器，每个拦截器来处理请求
        pipeline.addLast("系统自带的httpServerCodec",new HttpServerCodec());//在管道的最后面加一个组件，这个组件是netty提供的一个处理器
        pipeline.addLast("我的TestHttpServerHandler",new TestHttpServerHandler());//在管道的最后面加一个组件，这个组件是netty提供的一个处理器

    }
}
