package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketFinishLogin extends NetworkPacket {

    public PacketFinishLogin() {
        super(PacketType.COTNINUE_CONNECTING_RESPONSE);
    }

    @Override
    public void decode(Codec codec) {
    }
}
