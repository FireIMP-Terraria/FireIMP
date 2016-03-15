package net.fireimp.server.network.packets;

import io.netty.buffer.ByteBuf;

public class NetworkPacket {
    private final int id;
    private final ByteBuf data;

    public NetworkPacket(byte id, ByteBuf data) {
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
