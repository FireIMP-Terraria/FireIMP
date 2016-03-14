package net.fireimp.server.network.packets;

import io.netty.buffer.ByteBuf;

public class PacketIn {
    private final int id;
    private final ByteBuf data;

    public PacketIn(byte id, ByteBuf data) {
        this.id = id;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public ByteBuf getData() {
        return data;
    }
}
