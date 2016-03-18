package net.fireimp.server.util;

public class BitFlags {
    public static byte setBitValue(byte flags, int bit, boolean value) {
        if(value) {
            return setBit(flags, bit);
        } else {
            return unsetBit(flags, bit);
        }
    }

    public static byte setBit(byte flags, int bit) {
        return set(flags, 1 << Maths.clamp(bit, 0, 7));
    }

    public static byte unsetBit(byte flags, int bit) {
        return unset(flags, 1 << Maths.clamp(bit, 0, 7));
    }

    public static byte setMask(byte flags, int mask, boolean value) {
        if(value) {
            return set(flags, mask);
        } else {
            return unset(flags, mask);
        }
    }

    public static byte set(byte flags, int mask) {
        flags |= mask & 0xFF;
        return flags;
    }

    public static byte unset(byte flags, int mask) {
        flags &= ~(mask & 0xFF);
        return flags;
    }

    public static boolean getBit(byte flags, int bit) {
        return (flags & (1 << bit)) != 0;
    }

    public static boolean get(byte flags, int mask) {
        return (flags & mask) != 0;
    }
}
