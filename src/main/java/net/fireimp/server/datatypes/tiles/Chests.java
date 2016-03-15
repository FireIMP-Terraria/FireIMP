package net.fireimp.server.datatypes.tiles;

import net.fireimp.server.datatypes.StreamObject;
import net.fireimp.server.datatypes.Vec2i;
import net.fireimp.server.network.Codec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chests implements StreamObject {
    private List<Vec2i> chestLocations = new ArrayList<>();

    public Chests(Vec2i... chestLocations) {
        this.chestLocations.addAll(Arrays.asList(chestLocations));
    }

    public void addChests(Vec2i... locations) {
        chestLocations.addAll(Arrays.asList(locations));
    }

    @Override
    public void write(Codec codec) {
        int index = 0;
        for(Vec2i location : chestLocations) {
            codec.writeByte(index++);
            codec.writeByte(location.getX());
            codec.writeByte(location.getY());
        }
    }

    @Override
    public void read(Codec codec) {

    }
}
