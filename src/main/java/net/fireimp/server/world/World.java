package net.fireimp.server.world;

import com.google.common.collect.Lists;
import net.fireimp.server.Threads;
import net.fireimp.server.entities.Entity;
import net.fireimp.server.entities.Player;
import net.fireimp.server.util.Maths;
import net.fireimp.server.world.generator.WorldGenerator;

import java.util.*;

public class World {
    private final Tile[][] tiles;
    private Map<Integer, Entity> entities = new HashMap<>();
    private Map<Integer, Player> players = new HashMap<>();
    private final WorldSize size;
    private final WorldInfo worldInfo;
    private final WorldGenerator generator;
    private final int waterLayer;
    private final int lavaLayer;
    private Random random = new Random();
    private long worldTick = 0L;
    private long entityTick = 0L;

    public World(WorldSize size) {
        this.tiles = size.newTileSet();
        this.size = size;
        this.worldInfo = new WorldInfo("FireIMP");
        worldInfo.setMaxTilesX(size.getWidth());
        worldInfo.setMaxTilesY(size.getHeight());
        this.generator = new WorldGenerator(this);
        this.waterLayer = (int) (getWorldInfo().getRockLayer() + (double) getSize().getWidth()) / 2;
        this.lavaLayer = waterLayer + (50 + random.nextInt(80 - 50));
        Threads.worldService.submit(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    tick();
                    try {
                        Thread.sleep(17); //~ 1/60th of a second
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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
        if(id == -1) {
            id = entity.getId();
        }
        entities.put(id, entity);
    }

    public Entity getEntity(int id) {
        if(entities.containsKey(id)) {
            return entities.get(id);
        } else {
            return null;
        }
    }

    public List<Entity> getEntities() {
        return Lists.newArrayList(entities.values());
    }

    public void addOrUpdatePlayer(int id, Player player) {
        players.put(id, player);
    }

    public Player getPlayer(int id) {
        if(players.containsKey(id)) {
            return players.get(id);
        } else {
            return null;
        }
    }

    private void tick() {
        Threads.entityService.submit(new Runnable() {
            @Override
            public void run() {
                for(Entity entity : getEntities()) {
                    entity.update((int) entityTick);
                }
                entityTick++;
            }
        });
        worldTick++;
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

    public List<Player> getPlayers() {
        return Lists.newArrayList(players.values());
    }
}
