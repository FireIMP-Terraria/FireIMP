package net.fireimp.server.network.packets;

import net.fireimp.server.network.Codec;

public class NetworkPacket {
    protected final PacketType type;

    public NetworkPacket(PacketType type) {
        this.type = type;
    }

    public PacketType getType() {
        return type;
    }

    public void encode(Codec codec) {
        throw new IllegalStateException("Tried to encode while no encoder is set!");
    }

    public void decode(Codec byf) {
        throw new IllegalStateException("Tried to decode while no decoder is set!");
    }
}
