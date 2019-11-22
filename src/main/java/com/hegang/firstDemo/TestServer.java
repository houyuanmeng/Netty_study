package com.hegang.firstDemo;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Creat By ${侯某某} on 2019/11/21
 */

public class TestServer {
   public static void main(String[] args) throws InterruptedException {
       NioEventLoopGroup bossgroup = new NioEventLoopGroup();
       NioEventLoopGroup workerGroup = new NioEventLoopGroup();
       try {

           ServerBootstrap ss = new ServerBootstrap();
           //前面用于接收请求 后面用给来数据处理
           ss.group(bossgroup,workerGroup)
                   .channel(NioServerSocketChannel.class)//只链接一个通道，通过反射创建实例
                   .childHandler(new TestServerInitializer());//子处理器，也就是请求到来后由自己所编写的处理器


           //绑定端口 sync是实时更新，此方法会抛出异常
           ChannelFuture channelFutur = ss.bind(8899).sync();
           //关闭监听
           channelFutur.channel().closeFuture().sync();
       } finally {
           bossgroup.shutdownGracefully();
           workerGroup.shutdownGracefully();

       }
   }
}
