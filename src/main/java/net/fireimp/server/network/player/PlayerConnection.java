package net.fireimp.server.network.player;

import io.netty.channel.ChannelHandlerContext;
import net.fireimp.server.network.packets.NetworkPacket;

public class PlayerConnection {
    private ChannelHandlerContext ctx;
    public static int LAST_PLAYER_ID = 0;
    private int playerId;
    public PlayerConnection() {
        playerId = LAST_PLAYER_ID++;
    }
    public void setChannelHandlerContext(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public void sendPacket(NetworkPacket packet) {
        ctx.writeAndFlush(packet);
    }

    public void sendPackets(NetworkPacket... packets) {
        for(NetworkPacket packet : packets) {
            ctx.write(packet);
        }
        ctx.flush();
    }

    public ChannelHandlerContext getContext() {
        return ctx;
    }

    public int getPlayerId() {
        return playerId;
    }
}
