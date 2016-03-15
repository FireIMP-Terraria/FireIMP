package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketCompleteConnection extends NetworkPacket {
    public PacketCompleteConnection() {
        super(PacketType.COMPLETE_CONNECTION);
    }
    public void encode(Codec codec) {

    }
}
