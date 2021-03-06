package net.fireimp.server.network;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import net.fireimp.server.TerrariaServer;
import net.fireimp.server.network.handlers.PacketDecoder;
import net.fireimp.server.network.handlers.PacketEncoder;
import net.fireimp.server.network.handlers.PacketHandler;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

public class NetManager extends ChannelInitializer<SocketChannel> {
    private final int port;

    public NetManager(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(this)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
//                    .option(ChannelOption.SO_TIMEOUT, 60000)
                    .localAddress(port);
            bootstrap.bind().addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if(!future.isSuccess()) {
                        System.err.println(future.cause().getMessage());
                    } else {
                        System.out.println("Server running");
                    }
                }
            });
        } catch(Exception e) {
            // TODO: Log error
            e.printStackTrace();
        }
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        final PlayerConnection connection = new PlayerConnection();
        channel.pipeline().addLast("encoder", new PacketEncoder())
                .addLast("decoder", new PacketDecoder())
                .addLast("packet_handler", new PacketHandler(connection, TerrariaServer.getInstance().getWorld()));
    }
}
