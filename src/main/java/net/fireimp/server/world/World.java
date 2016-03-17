package net.fireimp.server.world;

import net.fireimp.server.util.Maths;

public class World {
    private final Tile[] tiles;
    private final WorldSize size;

    public World(WorldSize size) {
        this.tiles = size.newTileSet();
        this.size = size;
    }

    public WorldSize getSize() {
        return size;
    }

    public Tile getTileAt(int x, int y) {
        int index = Maths.clamp(x, 0, size.getWidth()-1) << 16 | Maths.clamp(y, 0, size.getHeight()-1);
        Tile tile = tiles[index];
        if(tile == null) {
            // Create new tile
            // TODO: Create empty tile and don't store to save RAM, when empty tile is modified add to tile set.
            tiles[index] = tile = new Tile((short) x, (short) y);
        }

        return tile;
    }
}
