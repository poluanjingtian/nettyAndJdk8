package com.shengsiyuan.netty.nettyTestSecond;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/27 0:23
 * Description:
 */

/**
 * 这里的泛型为String，说明传输的是String对象
 */
public class MyServeHandler extends SimpleChannelInboundHandler<String> {
    /**
     * @param ctx
     * @param cause
     * @throws Exception 异常的捕获，一般出现异常，就把连接关闭。
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * @param ctx，表示请求的上下文对象，可用于获取channel，远程地址等信息
     * @param msg，表示客户端发送过来的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " : " + msg);
        ctx.channel().writeAndFlush("from address " + UUID.randomUUID());
    }
}
