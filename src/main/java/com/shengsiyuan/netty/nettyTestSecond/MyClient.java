package com.shengsiyuan.netty.nettyTestSecond;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/27 0:32
 * Description:
 */
public class MyClient {
    public static void main(String[] args) throws Exception {
        // 客户端只需要一个时间循环组
        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            /**
             * 要点；1客户端的api只需要去掉Server；
             * 2、服务端中handler 和childHandler的区别是，handler是boss事件循环组处理。childHandler 是work事件循环组负责处理
             */
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitializer());
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }
}
