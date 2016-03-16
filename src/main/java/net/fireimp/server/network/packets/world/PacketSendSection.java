package net.fireimp.server.network.packets.world;

import net.fireimp.server.datatypes.Vec2i;
import net.fireimp.server.datatypes.enums.TileType;
import net.fireimp.server.datatypes.tiles.Chests;
import net.fireimp.server.datatypes.tiles.Sign;
import net.fireimp.server.datatypes.tiles.TileEntity;
import net.fireimp.server.datatypes.tiles.TileSection;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketSendSection extends NetworkPacket {
    private boolean compressed;
    private int xStart;
    private int yStart;
    private short width;
    private short height;
    private List<TileSection> tileSections = new ArrayList<>();
    private List<Sign> signs = new ArrayList<>();
    private List<TileEntity> tileEntities = new ArrayList<>();
    private Chests chests = new Chests();
    public PacketSendSection() {
        super(PacketType.SEND_SECTION);
    }

    public void addTiles(TileSection... tileSections) {
        this.tileSections.addAll(Arrays.asList(tileSections));
    }

    public void addSigns(Sign... signs) {
        this.signs.addAll(Arrays.asList(signs));
    }
    public void addChests(Vec2i... chests) {
        this.chests.addChests(chests);
    }

    @Override
    public void encode(Codec codec) {
        codec.writeBoolean(compressed);
        codec.writeInt(xStart);
        codec.writeInt(yStart);
        codec.writeShort(width);
        codec.writeShort(height);
        for(TileSection tileSection : tileSections) {
            tileSection.write(codec);
        }
        chests.write(codec);
        int index = 0;
        codec.writeShort(signs.size());
        for(Sign sign : signs) {
            sign.setIndex(index++);
            sign.write(codec);
        }
        codec.writeShort(tileEntities.size());
        for(TileEntity entity : tileEntities) {
            entity.write(codec);
        }
    }

    public int getStartX() {
        return xStart;
    }

    public void setStartX(int xStart) {
        this.xStart = xStart;
    }

    public int getStartY() {
        return yStart;
    }

    public void setStartY(int yStart) {
        this.yStart = yStart;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }
}
