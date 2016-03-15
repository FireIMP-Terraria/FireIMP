package net.fireimp.server.network.packets.world;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketRequestSection extends NetworkPacket {
    private int xSection;
    private int ySection;
    public PacketRequestSection() {
        super(PacketType.REQUEST_SECTION);
    }
    public void decode(Codec codec) {
        xSection = codec.readInt();
        ySection = codec.readInt();
    }

    public int getXSection() {
        return xSection;
    }

    public int getYSection() {
        return ySection;
    }
}
