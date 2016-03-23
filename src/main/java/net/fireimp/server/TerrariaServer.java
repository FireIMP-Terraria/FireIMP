package net.fireimp.server;

import net.fireimp.server.network.NetManager;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

public class TerrariaServer {
    public static World world;

    public static void main(String[] args) {
        new RawPacketSystem().run();
        world = new World(WorldSize.SMALL);
        world.generate();

        NetManager netManager = new NetManager(25565);
        netManager.start();
    }
}
