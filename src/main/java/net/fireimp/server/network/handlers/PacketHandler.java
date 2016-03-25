package net.fireimp.server.network.handlers;

import io.netty.channel.*;
import net.fireimp.server.datatypes.chat.MessageSection;
import net.fireimp.server.datatypes.enums.Achievement;
import net.fireimp.server.datatypes.enums.EntityType;
import net.fireimp.server.datatypes.enums.ItemPrefix;
import net.fireimp.server.entities.Entity;
import net.fireimp.server.entities.Player;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketChatMessage;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.network.packets.entity.PacketEntityUpdate;
import net.fireimp.server.network.packets.entity.PacketPlayerUpdate;
import net.fireimp.server.network.packets.login.*;
import net.fireimp.server.network.packets.world.PacketRequestSection;
import net.fireimp.server.network.packets.world.PacketSendSection;
import net.fireimp.server.network.packets.world.PacketWorldInfo;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.world.Tile;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

import java.awt.*;
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
            playerConnection.sendPacket(new PacketContinueConnecting(playerConnection.getPlayerId()));

        } else if(packet.getType() == PacketType.CONTINUE_CONNECTING_RESPONSE) {
            System.out.println("Creating fake world :o");
            playerConnection.sendPacket(new PacketWorldInfo(world.getWorldInfo()));

        } else if(packet.getType() == PacketType.REQUEST_SECTION) {
            PacketRequestSection requestSection = ((PacketRequestSection)packet);

            if(requestSection.getXSection() == -1 && requestSection.getYSection() == -1) {
                //Banner trick:
                playerConnection.sendPacket(new PacketSetStatus("You are playing on a FireIMP Server!                                                                  "));
                for(int startX = world.getWorldInfo().getSpawnX() - 100 - 200 * 2; startX < world.getWorldInfo().getSpawnX() + 100 + 200 * 2; startX += 200) {
//                    for (int startY = world.getWorldInfo().getSpawnY() - 75 - 150 * 2; startY < world.getWorldInfo().getSpawnY() + 75 + 140 * 2; startY += 150) {
//                    int startX = world.getWorldInfo().getSpawnX() - 100;
                    int startY = world.getWorldInfo().getSpawnY() - 75;
                        int width = 200;
                        int height = 150;
                        Tile[] tiles = new Tile[width * height];
                        int idx = 0;
                        for (int y = 0; y < 150; y++) {
                            for (int x = 0; x < 200; x++) {
                                tiles[idx++] = world.getTileAt(startX + x, startY + y);
                            }
                        }
                        PacketSendSection section = new PacketSendSection(startX, startY, width, height, tiles);
                        playerConnection.sendPacket(section);
                    }
                }
                    new Thread() {
                        public void run() {
                            try {
                                Thread.sleep(1000L);
                            } catch (InterruptedException e) {
                            }
                            playerConnection.sendPacket(new PacketCompleteConnection());

                            MessageSection item = new MessageSection().setItem(2701);
                            playerConnection.sendPacket(new PacketChatMessage(item.append(new MessageSection("Welcome to FireIMP!")).append(item), Color.RED));
                        }
                    }.start();
                }
//        }
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
