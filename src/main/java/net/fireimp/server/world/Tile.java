package net.fireimp.server.world;

import net.fireimp.server.network.Codec;
import net.fireimp.server.util.BitFlags;

public class Tile {
    private final short x;
    private final short y;
    private int type;

    private final BitFlags flags1 = new BitFlags();
    private final BitFlags flags2 = new BitFlags();
    private final BitFlags flags3 = new BitFlags();

    public Tile(short x, short y) {
        this.x = x;
        this.y = y;
        flags1.setMask(2, true);
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public void setTileId(int id) {
        this.type = id;
        boolean isShort = id >>> 8 != 0;
        flags1.setMask(32, isShort);
    }

    public void encode(Codec codec, int repeat) {
        // Apply repeat count
        if(repeat > 0) flags1.set(64);
        if((repeat & 0xFF00) != 0) flags1.set(128);
        // Send flags
        codec.writeByte(flags1.getValue());
        if(flags1.get(1) || flags2.get(1)) {
            // We can't send 3rd bit flags if we don't send second.
            codec.writeByte(flags2.getValue());
        }
        if(flags2.get(1)) {
            codec.writeByte(flags3.getValue());
        }

        // Send type
        if(flags1.get(32)) {
            codec.writeShort(type);
        } else {
            codec.writeByte(type);
        }

        // Send extra values
        if(flags1.get(128)) {
            codec.writeShort(repeat);
        } else if(flags1.get(64)) {
            codec.writeByte(repeat);
        }
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Tile)) return false;
        Tile t = (Tile) o;
        return t.type == type;
    }
}
