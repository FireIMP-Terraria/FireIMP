package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketConnectRequest extends NetworkPacket {
    private String version;

    public PacketConnectRequest() {
        super(PacketType.CONNECT_REQUEST);
    }

    @Override
    public void decode(Codec codec) {
        this.version = codec.readString();
    }

    public String getVersion() {
        return version;
    }
}
