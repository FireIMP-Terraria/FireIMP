package net.fireimp.server.world;

import net.fireimp.server.network.Codec;
import net.fireimp.server.util.BitFlags;

public class Tile {
    private final short x;
    private final short y;
    private int type = -1;

    private byte flags1 = 0;
    private byte flags2 = 0;
    private byte flags3 = 0;

    public Tile(short x, short y) {
        this.x = x;
        this.y = y;
//        flags1.setMask(2, true);
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    public int getTypeId() {
        return type;
    }

    public void setTileId(int id) {
        this.type = id;
        boolean isShort = id >>> 8 != 0;
        if(isShort) flags1 |= 32;
        boolean active = id >= 0;
        if(active) flags1 |= 2;
    }

    public void encode(Codec codec, int repeat) {
        // Send flags
        codec.writeByte(flags1);
        if((flags1 & 1) != 0 || (flags2 & 1) != 0) {
            // We can't send 3rd bit flags if we don't send second.
            codec.writeByte(flags2);
        }
        if(BitFlags.get(flags2, 1)) {
            codec.writeByte(flags3);
        }
        if(type < 0) {
            // inactive block
            return;
        }

        // Apply repeat count
        // TODO: Once set to true, always remains on true
        if(repeat > 0) flags1 |= 64;
        if((repeat & 0xFF00) != 0) flags1 |= 128;

        // Send type
        if(BitFlags.get(flags1, 32)) {
            codec.writeShort(type);
        } else {
            codec.writeByte(type);
        }

        // Send extra values
        if(BitFlags.get(flags1, 128)) {
            codec.writeShort(repeat);
        } else if(BitFlags.get(flags1, 64)) {
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
