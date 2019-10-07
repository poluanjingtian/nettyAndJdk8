package com.shengsiyuan.netty.nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/9/22 15:24
 * Description:
 */
public class TestHttpHandler extends SimpleChannelInboundHandler<HttpObject> {
   // channelRead0 读取客户端请求，并返回响应方法
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        // 如果不加这个判断条件使用curl 测试会报错，使用curl命令测试 "http://localhost:8899"
        // 判断是不是HTTPRequest请求
        if (msg instanceof HttpRequest) {
            System.out.println(msg.getClass());
            System.out.println(ctx.channel().remoteAddress());
            HttpRequest httpRequest = (HttpRequest) msg;
            URI uri = new URI(httpRequest.uri());
            // 判断url是否是favicon.ico
            if ("/favicon.ico".equals(uri.getPath())) {
                System.out.println("请求了favicon.ico");
                return;
            }
            /**
             * 上面这个代码是验证如果用浏览器访问
             * chrome和Firefox会发起两次请求，第二次是请求/favicon.ico 图标
             */
            System.out.println("请求方法名：" + httpRequest.method().name());
            //ByteBuf 是netty中极为重要的概念，代表响应返回的数据
            ByteBuf content = Unpooled.copiedBuffer("HelloWorld! " + Thread.currentThread().getName(), CharsetUtil.UTF_8);
            // 构造一个http响应，http 1.1 ,状态码为200；
            DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            // 如果只是调用write方法，仅仅是存在于缓冲区，并不会返回给客户端，需要调用writeAndFlush
            ctx.writeAndFlush(response);
        }
    }

    /**
     * 处理顺序如下
     * handlerAdded
     * channelRegistered
     * channelActive
     * 请求方法名：GET
     * (下面是断开连接后的处理)。
     * 如果是使用curl，连接会立刻关闭
     * 如果是浏览器，http1.0，短连接，也会立刻关闭；http1.1，是长连接，连接保持一段时间
     *channelInactive, channelUnregistered
     */

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded");
        super.handlerAdded(ctx);
    }
}
