package net.fireimp.server.network.handlers;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.channel.*;
import lombok.Getter;
import net.fireimp.server.network.listeners.LoginListener;
import net.fireimp.server.network.listeners.PacketIn;
import net.fireimp.server.network.listeners.PacketListener;
import net.fireimp.server.network.listeners.PlayerListener;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketChatMessage;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.network.player.NetPhase;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.world.World;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.*;

public class PacketHandler extends ChannelInboundHandlerAdapter {
    private final Map<PacketType, Set<EventInfo>> eventMap = Maps.newConcurrentMap();
    private final PlayerConnection playerConnection;

    public PacketHandler(PlayerConnection playerConnection, World world) {
        this.playerConnection = playerConnection;

        // Register listeners
        registerListener(new LoginListener(playerConnection, world));
        registerListener(new PlayerListener());
    }

    public void registerListener(PacketListener listener) {
        List<NetPhase> globalPhases = listener.getSupportedPhases();
        for(Method method : listener.getClass().getMethods()) {
            PacketIn properties = method.getAnnotation(PacketIn.class);
            if(properties == null) {
                continue;
            }
            if(method.getParameterTypes().length < 1) {
                continue;
            }

            PacketType type = PacketType.getTypeByClass(method.getParameterTypes()[0]);
            if(type == null) {
                continue;
            }

            List<NetPhase> phases = properties.value().length == 0 ? globalPhases : Arrays.asList(properties.value());
            Set<EventInfo> list = eventMap.get(type);
            if(list == null) {
                list = Sets.newConcurrentHashSet();
                eventMap.put(type, list);
            }

            list.add(new EventInfo(listener, method, phases));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NetworkPacket packet = (NetworkPacket) msg;
        System.out.println(packet.getType());

        Set<EventInfo> events = eventMap.get(packet.getType());
        if(events == null) {
            return;
        }

        for(EventInfo event : events) {
            if(!event.getPhases().contains(NetPhase.ALL) && !event.getPhases().contains(playerConnection.getPhase())) {
                continue;
            }

            event.getMethod().invoke(event.getInstance(), packet);
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

    @Getter
    private static class EventInfo {
        private final PacketListener instance;
        private final Method method;
        private final List<NetPhase> phases;

        private EventInfo(PacketListener instance, Method method, List<NetPhase> phases) {
            this.instance = instance;
            this.method = method;
            this.phases = phases;
        }
    }
}
