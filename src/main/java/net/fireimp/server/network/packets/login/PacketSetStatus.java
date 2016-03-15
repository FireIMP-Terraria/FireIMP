package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketSetStatus extends NetworkPacket {
    private String status;
    public PacketSetStatus() {
        super(PacketType.STATUS);
    }
    public PacketSetStatus(String status) {
        this();
        this.status = status;
    }
    public void encode(Codec codec) {
        codec.writeByte(1);
        codec.writeString(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
