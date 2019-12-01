package com.shishuwu.sample1.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 向服务端写数据
        ctx.writeAndFlush("客户端UUID:" + UUID.randomUUID());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 处理服务端返回的消息
        System.out.println("来自"+ctx.channel().remoteAddress() + "的数据, " + msg);
        // 还可以在接收到服务器消息后继续再写，然后服务端再继续回，这样一直反复
        // ctx.writeAndFlush("客户端UUID:" + UUID.randomUUID());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}