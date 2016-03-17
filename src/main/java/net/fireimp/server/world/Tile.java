package net.fireimp.server.world;

public class Tile {
    private final short x;
    private final short y;
    private int type;

    public Tile(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public void setTileId(int id) {
        this.type = id;
    }
}
