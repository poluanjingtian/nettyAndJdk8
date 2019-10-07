package com.shengsiyuan.netty.fifthexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/4 0:23
 * Description:
 * 基于websocket的长连接全双工的交互
 * 客户端发送给服务端，服务端接受并能响应给客户端，客户端通过前端页面js连接（目前大多数的浏览器都支持websocket）
 * 1、基于webSocket，ws协议，会出现一个101的状态码，表示旧的http协议转换升级为ws协议
 * 2.每次刷新页面后，会新建一个连接，旧的连接会被关闭
 * 3.不要以为连接就一定能关闭，在断网或者断电情况，是检测不出来是否断连接的。（所以前面要有心跳机制）
 */
public class MyServer {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WebSocketChannelInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(8899)).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
