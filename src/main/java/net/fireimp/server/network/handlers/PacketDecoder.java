package net.fireimp.server.network.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.fireimp.server.network.packets.NetworkPacket;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> list) throws Exception {
        short length = input.readShortLE();
        byte id = input.readByte();
        ByteBuf data = ctx.alloc().buffer(length - 3);
        input.readBytes(data);
//        input.release();
        list.add(new NetworkPacket(id, data));
    }
}
