package net.fireimp.server.datatypes.tiles;

import net.fireimp.server.datatypes.StreamObject;
import net.fireimp.server.datatypes.Vec2i;
import net.fireimp.server.network.Codec;

public class Sign implements StreamObject {
    private int index;
    private Vec2i location;
    private String text;

    public Sign(Vec2i location, String text) {
        this.location = location;
        this.text = text;
    }

    @Override
    public void write(Codec codec) {
        codec.writeShort(index);
        codec.writeShort(location.getX());
        codec.writeShort(location.getY());
        codec.writeString(text);
    }

    @Override
    public void read(Codec codec) {

    }

    public Vec2i getLocation() {
        return location;
    }

    public void setLocation(Vec2i location) {
        this.location = location;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
