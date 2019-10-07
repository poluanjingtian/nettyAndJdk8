package com.shengsiyuan.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/2 23:37
 * Description:
 */
public class MyChatClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());
            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                // 读取一行数据，回车及读取
                channel.writeAndFlush(bf.readLine() + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
