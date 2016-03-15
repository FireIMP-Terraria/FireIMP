package net.fireimp.server.network.player;

import io.netty.channel.ChannelHandlerContext;
import net.fireimp.server.network.packets.NetworkPacket;

public class PlayerConnection {
    private ChannelHandlerContext ctx;

    public void setChannelHandlerContext(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void sendPacket(NetworkPacket packet) {
        ctx.writeAndFlush(packet);
    }
}
