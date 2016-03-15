package net.fireimp.server.datatypes;

public class Bitflags {
    private byte flags = 0;

    public void setFlag(int mask, boolean enabled) {
        if(enabled) {
            setFlag(mask);
        } else {
            unsetFlag(mask);
        }
    }

    private void setFlag(int mask) {
        flags &= mask;
    }

    private void unsetFlag(int mask) {
        flags &= ~mask;
    }

    public boolean isFlagSet(int mask) {
        return (flags & mask) != 0;
    }

    public int getValue() {
        return flags;
    }
}
