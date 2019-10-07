package com.shengsiyuan.netty.fourthexample;

import com.shengsiyuan.netty.nettyTestSecond.MyServeHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/27 0:10
 * Description:
 */
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * 处理空闲状态事件的处理器
         * 三个参数，第一个表示多长时间没有读，就发送一个心跳包检测是否有连接
         * 第二个表示多长时间没有写，就发送一个心跳包检测是否有连接
         * 最好一个表示多长时间没有读和写就发送一个心跳包检测是否有连接
         */
        pipeline.addLast(new IdleStateHandler(5, 2, 3, TimeUnit.SECONDS));
        // 对空闲检测进一步处理的handler
        pipeline.addLast(new MyServerHandler());
    }
}
