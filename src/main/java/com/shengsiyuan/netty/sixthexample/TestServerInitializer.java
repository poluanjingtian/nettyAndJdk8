package com.shengsiyuan.netty.sixthexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/6 14:40
 * Description:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 主要是这四个handler，用户protoBuf编解码
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        //这个handler，需要传入一个参数MessageLite，即我们需要传输数据的那个实例（这里是MyDataInfo.Student）
        /**
         * 这个代码耦合强了，还必须指定类，加入要传连个类，或者每次不同的类呢？这样写肯定是不行的.
         */
        pipeline.addLast(new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast(new TestServerHandler());

    }
}
