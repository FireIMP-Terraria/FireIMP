package net.fireimp.server;

import net.fireimp.server.datatypes.enums.TerrariaVersion;
import net.fireimp.server.network.NetManager;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

import java.util.Random;

public class TerrariaServer {
    private static TerrariaServer instance;
    private World world;
    public static Random GLOBAL_RANDOM = new Random();

    private TerrariaServer() {
        new RawPacketSystem().run();
        world = new World(WorldSize.SMALL);
        world.generate();

        NetManager netManager = new NetManager(25565);
        netManager.start();
        instance = this;
    }
    public static void main(String[] args) {
        new TerrariaServer();
    }

    public static TerrariaServer getInstance() {
        return instance;
    }

    public World getWorld() {
        return world;
    }

}
