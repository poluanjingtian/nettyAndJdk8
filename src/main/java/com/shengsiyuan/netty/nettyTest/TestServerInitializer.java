package com.shengsiyuan.netty.nettyTest;

import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/22 0:54
 * Description:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    // 回调方法，在channel被注册时调用
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 一个管道，里面有很多channelHandler,这些就像拦截器，可以做很多事情
        ChannelPipeline pipeline = ch.pipeline();
        // 增加一个处理器，netty提供的并提供默认名字，但还是自己自定义较好
        /**
         * 注意这些new出来的对象都是多例的，每次new出来的对象，因为每个链接都是不同的用户
         */
        // HttpServerCodec完成http编解码，可查源码
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler", new TestHttpHandler());

    }
}
