package net.fireimp.server.network.handlers;

import io.netty.channel.*;
import net.fireimp.server.datatypes.enums.TileType;
import net.fireimp.server.datatypes.tiles.TileSection;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.network.packets.login.PacketCompleteConnection;
import net.fireimp.server.network.packets.login.PacketConnectRequest;
import net.fireimp.server.network.packets.login.PacketContinueConnecting;
import net.fireimp.server.network.packets.login.PacketSetStatus;
import net.fireimp.server.network.packets.world.PacketRequestSection;
import net.fireimp.server.network.packets.world.PacketSendSection;
import net.fireimp.server.network.packets.world.PacketWorldInfo;
import net.fireimp.server.network.player.PlayerConnection;

import java.net.InetSocketAddress;

public class PacketHandler extends ChannelInboundHandlerAdapter {
    private final PlayerConnection playerConnection;

    public PacketHandler(PlayerConnection playerConnection) {
        this.playerConnection = playerConnection;
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
            playerConnection.sendPacket(new PacketWorldInfo());
        } else if(packet.getType() == PacketType.REQUEST_SECTION) {
            PacketRequestSection requestSection = ((PacketRequestSection)packet);

            if(requestSection.getXSection() == -1 && requestSection.getYSection() == -1) {
                //Banner trick:
//                playerConnection.sendPacket(new PacketSetStatus("You are playing on a FireIMP Server!                                                                  "));
                playerConnection.sendPacket(new PacketCompleteConnection());

                PacketSendSection sendSection = new PacketSendSection();
                sendSection.setStartX(3200);
                sendSection.setStartY(320);
                sendSection.setWidth((short) 50);
                sendSection.setHeight((short) 500);
                sendSection.addTiles(new TileSection(TileType.SNOW_BLOCK, (short) (2500)));
                playerConnection.sendPacket(sendSection);
            } else {
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
