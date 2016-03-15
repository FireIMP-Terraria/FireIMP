package net.fireimp.server.datatypes.tiles;

import net.fireimp.server.datatypes.StreamObject;
import net.fireimp.server.datatypes.Vec2i;
import net.fireimp.server.datatypes.enums.TileEntityType;
import net.fireimp.server.network.Codec;

public class TileEntity implements StreamObject {
    private TileEntityType entityType;
    private int id;
    private Vec2i location;
    private byte itemType;
    private byte itemPrefix;
    private short itemStack;

    public TileEntity(TileEntityType entityType, int id, Vec2i location) {
        this.entityType = entityType;
        this.id = id;
        this.location = location;
    }

    @Override
    public void write(Codec codec) {
        codec.writeByte(entityType.ordinal());
        codec.writeInt(id);
        codec.writeShort(location.getX());
        codec.writeShort(location.getY());
        if(entityType == TileEntityType.ITEM_FRAME) {
            codec.writeShort(itemType);
            codec.writeShort(itemPrefix);
            codec.writeShort(itemStack);
        }
    }

    @Override
    public void read(Codec codec) {

    }

    public void setItemStack(short itemStack) {
        this.itemStack = itemStack;
    }

    public void setItemPrefix(byte itemPrefix) {
        this.itemPrefix = itemPrefix;
    }

    public void setItemType(byte itemType) {
        this.itemType = itemType;
    }
}
