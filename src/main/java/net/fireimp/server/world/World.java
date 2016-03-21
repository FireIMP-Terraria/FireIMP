package net.fireimp.server.world;

import net.fireimp.server.entities.Entity;
import net.fireimp.server.util.Maths;
import net.fireimp.server.world.generator.WorldGenerator;

import java.util.*;

public class World {
    private final Tile[][] tiles;
    private Map<Integer, Entity> entities = new HashMap<>();
    private final WorldSize size;
    private final WorldInfo worldInfo;
    private final WorldGenerator generator;
    private final int waterLayer;
    private final int lavaLayer;
    private Random random = new Random();

    public World(WorldSize size) {
        this.tiles = size.newTileSet();
        this.size = size;
        this.worldInfo = new WorldInfo("FireIMP");
        worldInfo.setMaxTilesX(size.getWidth());
        worldInfo.setMaxTilesY(size.getHeight());
        this.generator = new WorldGenerator(this);
        this.waterLayer = (int) (getWorldInfo().getRockLayer() + (double) getSize().getWidth()) / 2;
        this.lavaLayer = waterLayer + (50 + random.nextInt(80 - 50));
    }

    public WorldSize getSize() {
        return size;
    }

    public Tile getTileAt(int x, int y) {
        // TODO: Replace with section system
//        int index = Maths.clamp(x, 0, size.getWidth()-1) << 16 | Maths.clamp(y, 0, size.getHeight()-1);
//        int index = x * size.getHeight() + y;
        Tile tile = tiles[x][y];
        if(tile == null) {
            // Create new tile
            // TODO: Create empty tile and don't store to save RAM, when empty tile is modified add to tile set.
            tiles[x][y] = tile = new Tile((short) x, (short) y);
        }

        return tile;
    }

    public WorldInfo getWorldInfo() {
        return worldInfo;
    }

    public void generate() {
        generator.generate();
    }

    public void addOrUpdateEntity(int id, Entity entity) {
        entities.put(id, entity);
    }

    public Entity getEntity(int id) {
        if(entities.containsKey(id)) {
            return entities.get(id);
        } else {
            return null;
        }
    }

    public int getWaterLayer() {
        return waterLayer;
    }

    public int getLavaLayer() {
        return lavaLayer;
    }

    public Random getRandom() {
        return random;
    }
}
