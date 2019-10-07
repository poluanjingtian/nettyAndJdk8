package com.shengsiyuan.netty.fourthexample;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/3 23:13
 * Description:注意这里继承的不是SimpleChannelInboundHandler，而是他的父类ChannelInboundHandlerAdapter
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 出发了某个事件就会被调用
     * @param ctx 上下文信息对象
     * @param evt 事件对象
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 如果当前事件是IdleStateEvent类型，表示他是一个空闲状态
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent)evt;
            String eventType = null;
            // 判断event是什么状态，event.state()返回的是state枚举类型
            switch (event.state()) {
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            // 读写空闲时针对于server端来说的，server 没有接收到客户端的数据，即为读空闲
            // 没有发送消息，即为写空闲
            System.out.println(ctx.channel().remoteAddress() + "--- 超时事件" + eventType);
            // 如果不关闭，会一直循环判断
            ctx.channel().close();
        }
    }
}
