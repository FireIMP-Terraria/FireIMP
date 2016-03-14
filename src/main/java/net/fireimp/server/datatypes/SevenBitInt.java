package net.fireimp.server.datatypes;

public class SevenBitInt {
    private byte[] value;
    protected SevenBitInt(byte[] value) {
        this.value = value;
    }
    public static SevenBitInt fromInt(int value) {
        byte[] bytes = new byte[7];
        value = Math.abs(value);
        int idx = 0;
        while(value >= 128) {
            bytes[idx++] = (byte)(value | 128);
            value >>= 7;
        }
        bytes[idx++] = (byte)value;
        return new SevenBitInt(bytes);
    }
}
