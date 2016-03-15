package net.fireimp.server.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.fireimp.server.network.packets.NetworkPacket;

public class PacketEncoder extends MessageToByteEncoder<NetworkPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NetworkPacket networkPacket, ByteBuf output) throws Exception {
        output.writeShortLE(networkPacket.getData().readableBytes() + 3);
        output.writeByte(networkPacket.getId());
        output.writeBytes(networkPacket.getData());
    }
}
