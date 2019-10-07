package com.shengsiyuan.netty.nettyTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/22 0:08
 * Description:
 */
public class TestServer {

    public static void main(String[] args) throws Exception {
        /**
         * 时间循环组，就是死循环
         */
        // 仅接受连接，转给workGroup, 自己不做处理
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动服务器代码
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // childHandler子处理器，传入一个初始化参数TestServerInitializer的实例
            //  TestServerInitializer在channel被注册时，就会创建调用
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitializer());
            // 绑定一个端口并且同步，生成一个ChannelFuture对象
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}
