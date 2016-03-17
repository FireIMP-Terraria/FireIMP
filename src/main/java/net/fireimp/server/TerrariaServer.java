package net.fireimp.server;

import net.fireimp.server.network.NetManager;

public class TerrariaServer {
    public static void main(String[] args) {
        NetManager netManager = new NetManager(25565);
        netManager.start();
    }
}
