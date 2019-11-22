package com.hegang.thirdexample;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Creat By ${侯某某} on 2019/11/22
 */
public class MyChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new myChatClientInitializer());

            Channel channel=bootstrap.connect("localhost",8899).sync().channel();
            //每次从键盘录入信息，每次读取一行，每次按回车代表一行，录入到channel中，然后向服务器发送
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                //读取一行数据，回车即读取
                channel.writeAndFlush(br.readLine()+"\r\n");

            }

        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}

