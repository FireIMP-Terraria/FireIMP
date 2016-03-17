package net.fireimp.server.util;

public class BitFlags {
    private byte flags = (byte) 0;

    public boolean setBitValue(int bit, boolean value) {
        if(value) {
            return setBit(bit);
        } else {
            return unsetBit(bit);
        }
    }

    public boolean setBit(int bit) {
        return set(1 << Maths.clamp(bit, 0, 7));
    }

    public boolean unsetBit(int bit) {
        return unset(1 << Maths.clamp(bit, 0, 7));
    }

    public boolean setMask(int mask, boolean value) {
        if(value) {
            return set(mask);
        } else {
            return unset(mask);
        }
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

    public boolean getBit(int bit) {
        return (flags & (1 << bit)) != 0;
    }

    public boolean get(int mask) {
        return (flags & mask) != 0;
    }

    public byte getValue() {
        return flags;
    }
}
