package com.shengsiyuan.netty.fifthexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/4 0:37
 * Description:
 */
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 因为这是基于http协议的，所以需要使用http编解码器
        pipeline.addLast(new HttpServerCodec());
        // 以块的方式去写
        pipeline.addLast(new ChunkedWriteHandler());
        // http数据在传输过程中是分段的。HttpObjectAggregator，就是将多个段聚合起来
        pipeline.addLast(new HttpObjectAggregator(8192));
        /**
         * 对于webSocket，它的数据传输是以frame（帧）的形式传递的
         * 可以查看webSocketFrame，他有六个子类
         * /ws，表示webSocket的地址。
         */
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new TextWebSocketFrameHandler());
    }
}
