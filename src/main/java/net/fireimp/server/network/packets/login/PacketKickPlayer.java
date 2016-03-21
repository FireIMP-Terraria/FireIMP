package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketKickPlayer extends NetworkPacket {
    private String message;
    public PacketKickPlayer() {
        super(PacketType.DISCONNECT);
    }

    public PacketKickPlayer(String message) {
        this();
        this.message = message;
    }

    @Override
    public void encode(Codec codec) {
        codec.writeString(message);
    }

}
