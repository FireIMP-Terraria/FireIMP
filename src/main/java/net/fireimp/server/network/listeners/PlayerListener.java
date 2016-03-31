package net.fireimp.server.network.listeners;

import net.fireimp.server.TerrariaServer;
import net.fireimp.server.datatypes.enums.EntityType;
import net.fireimp.server.entities.EntitySlime;
import net.fireimp.server.entities.Player;
import net.fireimp.server.network.packets.entity.PacketPlayerUpdate;
import net.fireimp.server.network.player.NetPhase;

import java.util.Collections;
import java.util.List;

public class PlayerListener implements PacketListener {
    @PacketIn(NetPhase.PLAYING)
    public void onPlayerUpdate(PacketPlayerUpdate playerUpdate) {
        Player player = TerrariaServer.getInstance().getWorld().getPlayer(playerUpdate.getPlayerId());
        player.setLocation(playerUpdate.getLocation());
        player.setVelocity(playerUpdate.getVelocity().getX(), playerUpdate.getVelocity().getY());
        player.updateTileMap();
        EntitySlime slime = new EntitySlime(EntityType.BLUE_SLIME);
        slime.setLocation(player.getLocation());
        TerrariaServer.getInstance().getWorld().addOrUpdateEntity(-1, slime);
    }

    @Override
    public List<NetPhase> getSupportedPhases() {
        return Collections.singletonList(NetPhase.PLAYING);
    }
}
