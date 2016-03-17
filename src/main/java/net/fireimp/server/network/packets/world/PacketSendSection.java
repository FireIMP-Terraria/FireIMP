package net.fireimp.server.network.packets.world;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;
import net.fireimp.server.world.Tile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;

public class PacketSendSection extends NetworkPacket {
    private boolean compressed = false;
    private int xStart;
    private int yStart;
    private int width;
    private int height;
    private Tile[] tiles;

    public PacketSendSection() {
        super(PacketType.SEND_SECTION);
    }

    public PacketSendSection(int xStart, int yStart, int width, int height, Tile[] tiles) {
        this(xStart, yStart, width, height, tiles, true); // compress by default
    }

    public PacketSendSection(int xStart, int yStart, int width, int height, Tile[] tiles, boolean compressed) {
        this();
        this.xStart = xStart;
        this.yStart = yStart;
        this.width = width;
        this.height = height;
        this.tiles = tiles;
        this.compressed = compressed;
    }

    @Override
    public void encode(Codec codec) {
        // Compression
        Codec originalCodec = codec;
        codec.writeBoolean(compressed);
        if(compressed) {
            codec = codec.newCodec();
        }

        // Write data
        codec.writeInt(xStart);
        codec.writeInt(yStart);
        codec.writeShort(width);
        codec.writeShort(height);
        for(int i = 0; i < tiles.length; i++) {
            int successive = 0;
            while(tiles[i + ++successive].equals(tiles[i]) && successive < Short.MAX_VALUE);
            tiles[i].encode(codec, successive - 1);
            i += successive - 1; // Skip tiles if successive
        }
        codec.writeShort(0); // no chests
        codec.writeShort(0); // no signs
        codec.writeShort(0); // no tile entities

        // Compression
        if(compressed) {
            byte[] uncompressed = codec.toByteArray(true);
            Deflater deflater = new Deflater();
            deflater.setInput(uncompressed);

            // Deflate (compress)
            byte[] buffer = new byte[1024];
            while(!deflater.finished()) {
                int length = deflater.deflate(buffer);
                originalCodec.writeBytes(buffer, 0, length);
            }
        }
    }

    @Override
    public void decode(Codec codec) {
        this.compressed = codec.readBoolean();
        if(compressed) {
            try {
                // Read remaining data
                byte[] data = new byte[codec.readableBytes()];
                codec.readBytes(data);

                // Create inflater
                Inflater inflater = new Inflater(true);
                inflater.setInput(data);

                // Write to buffer
                ByteBuf buf = Unpooled.buffer();
                byte[] buffer = new byte[1024];
                while(!inflater.finished()) {
                    int length = inflater.inflate(buffer);
                    buf.writeBytes(buffer, 0, length);
                }

                codec = new Codec(buf);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        this.xStart = codec.readInt();
        this.yStart = codec.readInt();
        this.width = codec.readShort();
        this.height = codec.readShort();
    }

    @Override
    public String toString() {
        return "PacketSendSection{" +
                "compressed=" + compressed +
                ", xStart=" + xStart +
                ", yStart=" + yStart +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
