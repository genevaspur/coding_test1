package com.hslee.coding_test1.utility;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WebSocketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    private final AtomicInteger count = new AtomicInteger(1);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if (frame instanceof TextWebSocketFrame textFrame) {
            int receivedCount = count.getAndIncrement();
            System.out.println("Received(" + receivedCount + "): " + textFrame.text());

            String message = switch (textFrame.text()) {
                case "ping" -> "pong";
                case "pong" -> "ping";
                default -> textFrame.text();
            };

            ctx.channel().eventLoop().schedule(() -> {
                System.out.println("Send: " + message + "(" + receivedCount + ")");
                ctx.writeAndFlush(new TextWebSocketFrame("Received: " + message + "(" + receivedCount + ")"));
            }, 3, TimeUnit.SECONDS);
        } else {
            ctx.writeAndFlush(new TextWebSocketFrame("Unsupported type"));
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        System.out.println("WebSocketHandler added");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        System.out.println("WebSocketHandler removed");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
