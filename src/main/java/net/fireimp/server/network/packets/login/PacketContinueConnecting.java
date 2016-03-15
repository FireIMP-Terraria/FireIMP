package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketContinueConnecting extends NetworkPacket {
    private int playerID;

    public PacketContinueConnecting() {
        super(PacketType.CONTINUE_CONNECTING);
    }

    public PacketContinueConnecting(int playerID) {
        this();
        this.playerID = playerID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public void encode(Codec codec) {
        codec.writeByte(playerID);
    }
}
