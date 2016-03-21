package net.fireimp.server.network.packets.entity;

import com.sun.org.apache.bcel.internal.classfile.Code;
import net.fireimp.server.datatypes.Bitflags;
import net.fireimp.server.datatypes.enums.EntityType;
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
    public void decode(Codec codec) {
        Entity entity = new Entity();
        entity.setId(codec.readShort());
        entity.setLocation(codec.readFloat(), codec.readFloat());
        entity.setVelocity(codec.readFloat(), codec.readFloat());
        entity.setTargetId(codec.readByte());
        byte flags = codec.readByte();
        System.out.println("Found flags: " + flags);
        entity.setEntityType(EntityType.fromNetworkId(codec.readShort()));
        entity.setHealth(codec.readInt());
        this.entity = entity;
    }
    @Override
    public String toString() {
        return "ID: " + entity.getId()
                + "\nLocation: " + entity.getLocation().getX() + ", " + entity.getLocation().getY()
                + "\nVelocity: " + entity.getVelocity().getX() + ", " + entity.getVelocity().getY()
                + "\nTarget: " + entity.getTargetId()
                + "\nFull health: " + (entity.getHealth() == entity.getMaxHealth())
                + "\nEntity type: " + entity.getEntityType().name()
                + "\nHealth: " + entity.getHealth();
    }
    @Override
    public void encode(Codec codec) {
        codec.writeShort(entity.getId());
        codec.writeFloat((float) entity.getLocation().getX());
        codec.writeFloat((float) entity.getLocation().getY());
        codec.writeFloat((float) entity.getVelocity().getX());
        codec.writeFloat((float) entity.getVelocity().getY());
        codec.writeByte(entity.getTargetId());
        byte flag = 0;
        if(entity.getLocation().getDirectionX() == Direction.RIGHT) {
            flag = BitFlags.setBit(flag, 1);
        }
        if(entity.getLocation().getDirectionY() == Direction.UP) {
            flag = BitFlags.setBit(flag, 2);
        }
        flag = BitFlags.setBit(flag, 4);
        flag = BitFlags.setBit(flag, 8);
        flag = BitFlags.setBit(flag, 16);
        flag = BitFlags.setBit(flag, 32);
        if(entity.getHealth() == entity.getMaxHealth()) {
            flag = BitFlags.setBit(flag, 128);
        }
        codec.writeByte(flag);
        codec.writeShort(entity.getEntityType().getNetworkID());
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
