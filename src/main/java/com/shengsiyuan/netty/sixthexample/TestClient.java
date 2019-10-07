package com.shengsiyuan.netty.sixthexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/6 14:55
 * Description:
 */
public class TestClient {
    public static void main(String[] args) throws  Exception{
        //事件循环组,只有一个循环组
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new TestClientInitializer());
            ChannelFuture channelFuture =bootstrap.connect("localhost",8899).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}
