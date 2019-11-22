package com.hegang.secondDemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * Creat By ${侯某某} on 2019/11/21
 */
public class myServerInitializer  extends ChannelInitializer<SocketChannel> {
    @Override
    //客户端一旦跟服务器链接initChannel方法就会被调用
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //给管道里加入解码器
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,7));
       //编码器
        pipeline.addLast(new LengthFieldPrepender(4));
       //解码格式
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //最后添加一个自己的处理器
        pipeline.addLast(new MyServerHandler());



    }
}
