package net.fireimp.server.util;

public class BitFlags {
    private byte flags = (byte) 0;

    public boolean setBit(int bit) {
        return set(1 << Maths.clamp(bit, 0, 7));
    }

    public boolean unsetBit(int bit) {
        return unset(1 << Maths.clamp(bit, 0, 7));
    }

    public boolean set(int mask) {
        byte copyOf = flags;
        flags |= mask & 0xFF;
        return flags != copyOf;
    }

    public boolean unset(int mask) {
        byte copyOf = flags;
        flags &= ~(mask & 0xFF);
        return flags != copyOf;
    }

    public byte getValue() {
        return flags;
    }
}
