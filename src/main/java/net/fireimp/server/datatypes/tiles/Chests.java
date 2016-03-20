package net.fireimp.server.datatypes.tiles;

import net.fireimp.server.datatypes.StreamObject;
import net.fireimp.server.datatypes.Vec2;
import net.fireimp.server.network.Codec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chests implements StreamObject {
    private List<Vec2> chestLocations = new ArrayList<>();

    public Chests(Vec2... chestLocations) {
        this.chestLocations.addAll(Arrays.asList(chestLocations));
    }

    public void addChests(Vec2... locations) {
        chestLocations.addAll(Arrays.asList(locations));
    }

    @Override
    public void write(Codec codec) {
        int index = 0;
        for(Vec2 location : chestLocations) {
            codec.writeByte(index++);
            codec.writeByte((int) location.getX());
            codec.writeByte((int) location.getY());
        }
    }

    @Override
    public void read(Codec codec) {

    }
}
