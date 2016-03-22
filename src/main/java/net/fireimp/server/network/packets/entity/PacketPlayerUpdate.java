package net.fireimp.server.network.packets.entity;

import net.fireimp.server.datatypes.Location;
import net.fireimp.server.datatypes.Vec2;
import net.fireimp.server.entities.Direction;
import net.fireimp.server.entities.Player;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.util.BitFlags;

public class PacketPlayerUpdate extends NetworkPacket {
    private int playerId;
    private byte selectedItemSlot = 0;
    private Location location = Location.ZERO;
    private Vec2 velocity = new Vec2(0, 0);
    private byte controlsFlag = 0;
    private byte pulleyFlag = 0;
    public PacketPlayerUpdate() {
        super(PacketType.UPDATE_PLAYER);
    }

    public PacketPlayerUpdate(Player player) {
        super(PacketType.UPDATE_PLAYER);
        this.playerId = player.getId();
        this.selectedItemSlot = player.getSelectedItem();
        this.location = player.getLocation();
    }

    public void encode(Codec codec) {
        codec.writeByte(playerId);
        codec.writeByte(0);
        codec.writeByte(0);
        codec.writeByte(selectedItemSlot);
        codec.writeFloat((float) location.getX());
        codec.writeFloat((float) location.getY());
        codec.writeFloat(0f);
        codec.writeFloat(0f);
    }

    public void decode(Codec codec) {
        playerId = codec.readByte();
        controlsFlag = codec.readByte();
        pulleyFlag = codec.readByte();
        selectedItemSlot = codec.readByte();
        location.setX(codec.readFloat());
        location.setY(codec.readFloat());
        location.setDirectionX(BitFlags.get(controlsFlag, 64) ? Direction.RIGHT : Direction.LEFT);
        if(BitFlags.get(pulleyFlag, 2)) {
            velocity.setX(codec.readFloat());
            velocity.setX(codec.readFloat());
        }
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(byte playerId) {
        this.playerId = playerId;
    }

    public byte getControlsFlag() {
        return controlsFlag;
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vec2 velocity) {
        this.velocity = velocity;
    }
}
