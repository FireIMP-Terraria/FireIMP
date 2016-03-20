package net.fireimp.server.network.packets.entity;

import net.fireimp.server.datatypes.Bitflags;
import net.fireimp.server.entities.Direction;
import net.fireimp.server.entities.Entity;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.util.BitFlags;

public class PacketEntityUpdate extends NetworkPacket {
    private Entity entity;
    public PacketEntityUpdate() {
        super(PacketType.NPC_UPDATE);
    }
    public PacketEntityUpdate(Entity entity) {
        super(PacketType.NPC_UPDATE);
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void encode(Codec codec) {
//        codec.writeByte(0x02);
//        codec.writeFloat((float) entity.getLocation().getX());
//        codec.writeFloat((float) entity.getLocation().getY());
//        codec.writeByte(0x46);
//        codec.writeByte(0xb2);
//        codec.writeByte(0x94);
//        codec.writeByte(0xf1);
//        codec.writeByte(0xbf);
//        codec.writeByte(0xf1);
//        codec.writeByte(0xc4);
//        codec.writeByte(0x4c);
//        codec.writeByte(0x3e);
//        codec.writeByte(0x00);
//        codec.writeByte(0x84);
//        codec.writeByte(0x00);
//        codec.writeByte(0x00);
//        codec.writeByte(0x80);
//        codec.writeByte(0x3f);
//        codec.writeByte(0x37);
//        codec.writeByte(0x00);
//        codec.writeByte(0xff);
        codec.writeShort(entity.getEntityType().getNetworkID());
        codec.writeFloat((float) entity.getLocation().getX());
        codec.writeFloat((float) entity.getLocation().getY());
        codec.writeFloat((float) entity.getVelocity().getX());
        codec.writeFloat((float) entity.getVelocity().getY());
        codec.writeByte(entity.getTargetId());
        byte flag = 0;
        if(entity.getLocation().getDirectionX() == Direction.RIGHT) {
            BitFlags.setBit(flag, 1);
        }
        if(entity.getLocation().getDirectionY() == Direction.UP) {
            BitFlags.setBit(flag, 2);
        }
        if(entity.getHealth() == entity.getMaxHealth()) {
            BitFlags.setBit(flag, 128);
        }
        codec.writeByte(flag);
        codec.writeShort(entity.getId());
        if(entity.getHealth() != entity.getMaxHealth()) {
            if (entity.getHealth() > Short.MAX_VALUE) {
                codec.writeInt(entity.getHealth());
            } else if (entity.getHealth() > Byte.MAX_VALUE) {
                codec.writeShort(entity.getHealth());
            } else {
                codec.writeByte(entity.getHealth());
            }
        }
//        codec.writeByte(1);
    }
}
