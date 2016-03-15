package net.fireimp.server.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        if(input.readableBytes() < 2) {
            // Invalid packet length
            return;
        }

        short length = input.readShortLE();
        byte id = input.readByte();
        ByteBuf data = ctx.alloc().buffer(length  - 3);
        input.readBytes(data);
        PacketType type = PacketType.getTypeById(id);
        if(type == null) {
            System.out.println("unknown type: " + id);
            return;
        }

        Class<?> typeClass = PacketType.getClassByType(type);
        if(typeClass == null) {
            System.out.println("Unsupported packet: " + type.toString());
            return;
        }

        // Decode packet data into new packet
        NetworkPacket packet = (NetworkPacket) typeClass.newInstance();
        packet.decode(new Codec(data));
        list.add(packet);

        // Release resources
        data.release();
    }
}
