package net.fireimp.server.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;

public class PacketEncoder extends MessageToByteEncoder<NetworkPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NetworkPacket packet, ByteBuf output) throws Exception {
        ByteBuf buf = ctx.alloc().buffer();
        Codec codec = new Codec(buf);

        // Encode packet
        codec.writeByte(packet.getType().getId());
        packet.encode(codec);

        // Write out
        output.writeShortLE(buf.readableBytes() + 2);
        output.writeBytes(buf);

        // Release resources
        buf.release();
    }
}
