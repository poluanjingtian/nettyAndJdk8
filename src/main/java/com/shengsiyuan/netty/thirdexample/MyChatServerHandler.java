package com.shengsiyuan.netty.thirdexample;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/2 15:48
 * Description:
 */

/**
 * 泛型String是指传输的对象类型
 */
public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 定义一个channelGroup对象，实质上是一个容器，用于保存服务端与客户端的连接对象，
     * 一个channel 表示一个连接对象
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息" + msg + "\n");
            } else {
                ch.writeAndFlush("[自己 : ]" + msg);
            }
        });
    }

    /**
     * 表示连接处于活动状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线啦！！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "离线啦！！");
    }

    /**
     * 异常的捕获，一旦出现异常，关闭连接
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * @param ctx
     * @throws Exception
     * 表示连接建立，一旦连接第一个执行
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        /**
         * channelGroup的writeAndFlush有点特别，他将循环对里面每一个channel进行输出
         * 如：假如A上线，会通知channelGroup其他channel，但是不会通知A，因为此时没有加入A
         * 如果也想通知自己，那么在输出前将自己加入channelGroup就好（注意他们的顺序）
         */
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端]" + channel.remoteAddress() + "加入！\n");
        channelGroup.add(channel);
    }

    /**
     * 断开连接时执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[客户端- ]" + channel.remoteAddress() + "离开 \n");
        /**
         * 断开连接时，netty会自动调用remove方法,无须手动调用
         */
        // channelGroup.remove(channel);
        System.out.println(channelGroup.size());
    }
}
