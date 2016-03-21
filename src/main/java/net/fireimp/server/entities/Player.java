package net.fireimp.server.entities;

import net.fireimp.server.network.player.PlayerConnection;

public class Player extends Entity {
    private PlayerConnection networkHandle;
    private byte selectedItem;

    public Player(PlayerConnection networkHandle) {
        super(null);
        this.networkHandle = networkHandle;
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
