package net.fireimp.server.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;

public class Codec {
    private final ByteBuf buf;

    public Codec(ByteBuf buf) {
        this.buf = buf;
    }

    public long readLong() {
        return buf.readLongLE();
    }

    public double readDouble() {
        return buf.readDouble();
    }

    public int readInt() {
        return buf.readIntLE();
    }

    public float readFloat() {
        return buf.readFloat();
    }

    public short readShort() {
        return buf.readShortLE();
    }

    public byte readByte() {
        return buf.readByte();
    }

    public boolean readBoolean() {
        return buf.readBoolean();
    }

    public void readBytes(byte[] bytes) {
        buf.readBytes(bytes);
    }

    public int read7BitInt() {
        int out = 0;
        int bytes = 0;
        while(true) {
            int in = readByte();
            out |= (in & 0x7f) << (bytes++ * 7);
            if((in & 0x80) == 0) {
                break;
            }
        }
        return out;
    }

    public String readString() {
        int length = read7BitInt();
        byte[] bytes = new byte[length];
        readBytes(bytes);
        return new String(bytes, Charsets.US_ASCII);
    }

    public long readUnsignedInt() {
        return buf.readUnsignedIntLE();
    }

    public int readUnsignedShort() {
        return buf.readUnsignedShortLE();
    }

    public short readUnsignedByte() {
        return buf.readUnsignedByte();
    }

    public void writeLong(long value) {
        buf.writeLongLE(value);
    }

    public void writeDouble(double value) {
        buf.writeDouble(value);
    }

    public void writeInt(int value) {
        buf.writeIntLE(value);
    }

    public void writeFloat(float value) {
        buf.writeFloat(value);
    }

    public void writeShort(int value) {
        buf.writeShortLE(value);
    }

    public void writeByte(int value) {
        buf.writeByte(value);
    }

    public void writeBoolean(boolean value) {
        buf.writeBoolean(value);
    }

    public void write7BitInt(int value) {
        while(value > 0) {
            int bit = value & 0x7F; // get last 7 bits
            value >>>= 7; // remove last 7 bits
            if(value > 0) {
                bit |= 0x80; // set flag for next byte coming
            }
            writeByte(bit); // write
        }
    }

    public void writeString(String value) {
        write7BitInt(value.length());
        buf.writeBytes(value.getBytes(Charsets.US_ASCII));
    }

    public void writeBytes(byte[] bytes, int start, int length) {
        buf.writeBytes(bytes, start, length);
    }

    public int readableBytes() {
        return buf.readableBytes();
    }

    public Codec newCodec() {
        return new Codec(buf.alloc().buffer());
    }

    public byte[] toByteArray(boolean release) {
        buf.readerIndex(0);
        byte[] buffer = new byte[buf.readableBytes()];
        buf.readBytes(buffer);
        if(release) {
            buf.release();
        }
        return buffer;
    }
}
