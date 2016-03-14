package net.fireimp.server.network.handlers;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import net.fireimp.server.network.packets.PacketIn;

import java.net.InetSocketAddress;

public class PacketHandler extends ChannelDuplexHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        PacketIn packet = (PacketIn) msg;
        System.out.println("Received packet with id " + packet.getId() + " and length " + packet.getData().readableBytes());
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress address = (InetSocketAddress)  ctx.channel().remoteAddress();
        System.out.println(address.getAddress().getHostName() + " connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress address = (InetSocketAddress)  ctx.channel().remoteAddress();
        System.out.println(address.getAddress().getHostName() + " disconnected");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
