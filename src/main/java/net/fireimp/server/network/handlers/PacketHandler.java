package net.fireimp.server.network.handlers;

import io.netty.channel.*;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.network.packets.login.PacketCompleteConnection;
import net.fireimp.server.network.packets.login.PacketConnectRequest;
import net.fireimp.server.network.packets.login.PacketContinueConnecting;
import net.fireimp.server.network.packets.world.PacketRequestSection;
import net.fireimp.server.network.packets.world.PacketSendSection;
import net.fireimp.server.network.packets.world.PacketWorldInfo;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.world.Tile;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

import java.net.InetSocketAddress;

public class PacketHandler extends ChannelInboundHandlerAdapter {
    private final PlayerConnection playerConnection;
    private final World world;

    public PacketHandler(PlayerConnection playerConnection, World world) {
        this.playerConnection = playerConnection;
        this.world = world;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NetworkPacket packet = (NetworkPacket) msg;
        System.out.println(packet.getType());
        if(packet.getType() == PacketType.CONNECT_REQUEST) {
            System.out.println(((PacketConnectRequest)packet).getVersion());
            playerConnection.sendPacket(new PacketContinueConnecting(0));
        } else if(packet.getType() == PacketType.CONTINUE_CONNECTING_RESPONSE) {
            System.out.println("Creating fake world :o");
            playerConnection.sendPacket(new PacketWorldInfo(world.getWorldInfo()));
        } else if(packet.getType() == PacketType.REQUEST_SECTION) {
            PacketRequestSection requestSection = ((PacketRequestSection)packet);

            if(requestSection.getXSection() == -1 && requestSection.getYSection() == -1) {
                //Banner trick:
//                playerConnection.sendPacket(new PacketSetStatus("You are playing on a FireIMP Server!                                                                  "));
                int startX = world.getWorldInfo().getSpawnX() - 100;
                int startY = world.getWorldInfo().getSpawnY() - 75;
                int width = 200;
                int height = 150;
                Tile[] tiles = new Tile[width * height];
                int idx = 0;
                for(int x = 0; x < 200; x++) {
                    for(int y = 0; y < 150; y++) {
                        tiles[idx++] = world.getTileAt(x, y);
                    }
                }
                PacketSendSection section = new PacketSendSection(startX, startY, width, height, tiles);
                playerConnection.sendPacket(section);
                playerConnection.sendPacket(new PacketCompleteConnection());
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        playerConnection.setChannelHandlerContext(ctx);
        InetSocketAddress address = (InetSocketAddress)  ctx.channel().remoteAddress();
        System.out.println(address.getAddress().toString() + " connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress address = (InetSocketAddress)  ctx.channel().remoteAddress();
        System.out.println(address.getAddress().toString() + " disconnected");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
