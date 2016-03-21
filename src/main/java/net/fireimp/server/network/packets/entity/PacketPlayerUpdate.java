package net.fireimp.server.network.packets.entity;

import net.fireimp.server.datatypes.Location;
import net.fireimp.server.datatypes.enums.ControlAction;
import net.fireimp.server.entities.Player;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketPlayerUpdate extends NetworkPacket {
    private int playerId;
    private byte selectedItemId = 0;
    private Location location = Location.ZERO;
    public PacketPlayerUpdate() {
        super(PacketType.UPDATE_PLAYER);
    }

    public PacketPlayerUpdate(Player player) {
        super(PacketType.UPDATE_PLAYER);
        this.playerId = player.getId();
        this.selectedItemId = player.getSelectedItem();
        this.location = player.getLocation();
    }

    public void encode(Codec codec) {
        codec.writeByte(playerId);
        codec.writeByte(0);
        codec.writeByte(0);
        codec.writeByte(selectedItemId);
        codec.writeFloat((float) location.getX());
        codec.writeFloat((float) location.getY());
        codec.writeFloat(0f);
        codec.writeFloat(0f);
    }

    public void decode(Codec codec) {
//        playerId = codec.readByte();
//        codec.readByte();
//        codec.readByte();
//        selectedItemId = codec.readByte();
//        location.setX(codec.readFloat());
//        location.setY(codec.readFloat());
//        codec.readFloat();
//        codec.readFloat();
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(byte playerId) {
        this.playerId = playerId;
    }
}
