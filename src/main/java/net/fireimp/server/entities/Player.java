package net.fireimp.server.entities;

import net.fireimp.server.TerrariaServer;
import net.fireimp.server.network.packets.world.PacketSendSection;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.util.BoundingBox;
import net.fireimp.server.util.Maths;
import net.fireimp.server.util.Vec2;
import net.fireimp.server.world.Tile;
import net.fireimp.server.world.World;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    private PlayerConnection networkHandle;
    private byte selectedItem;
    private BoundingBox loadedTiles = BoundingBox.ZERO;

    public Player(PlayerConnection networkHandle) {
        super(null);
        this.networkHandle = networkHandle;
        setLocation(TerrariaServer.getInstance().getWorld().getWorldInfo().getSpawnLocation());
        updateTileMap();
    }

    public void updateTileMap() {
        World world = TerrariaServer.getInstance().getWorld();
        Vec2 location = getLocation().divide(16);
        BoundingBox tilesToLoad = new BoundingBox(new Vec2(location.getX(), location.getY()), new Vec2(location.getX(), location.getY()));
        tilesToLoad = tilesToLoad.expandOut(200, 75); //This should equate to a bit more than 1920 x 1080
        List<Tile> tiles = new ArrayList<>();
        Vec2 min = tilesToLoad.min();//.divide(16);
        Vec2 max = tilesToLoad.max();//.divide(16);
        min.setX(Maths.clamp(min.getX(), 0, world.getSize().getWidth()));
        min.setY(Maths.clamp(min.getY(), 0, world.getSize().getHeight()));
        max.setX(Maths.clamp(max.getX(), 0, world.getSize().getWidth()));
        max.setY(Maths.clamp(max.getY(), 0, world.getSize().getHeight()));
        for(int y = (int) min.getY(); y < max.getY() && y < world.getSize().getHeight(); y++) {
            for(int x = (int) min.getX(); x < max.getX() && x < world.getSize().getWidth(); x++) {
               /* if(loadedTiles.contains(new Vec2(x, y))) {
                    continue;
                }*/
                tiles.add(world.getTileAt(x, y));
            }
        }
        PacketSendSection sectionPacket = new PacketSendSection((int) min.getX(), (int) min.getY(),
                (int) tilesToLoad.getWidth(), (int) tilesToLoad.getHeight(), tiles.toArray(new Tile[tiles.size()]));
        networkHandle.sendPacket(sectionPacket);
    }

    public byte getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(byte selectedItem) {
        this.selectedItem = selectedItem;
    }

    public PlayerConnection getNetworkHandle() {
        return networkHandle;
    }
}
