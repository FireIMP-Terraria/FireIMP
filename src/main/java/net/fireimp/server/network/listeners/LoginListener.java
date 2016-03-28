package net.fireimp.server.network.listeners;

import com.google.common.collect.Lists;
import net.fireimp.server.world.World;
import net.fireimp.server.network.packets.login.PacketConnectRequest;
import net.fireimp.server.network.packets.login.PacketContinueConnecting;
import net.fireimp.server.network.packets.login.PacketFinishLogin;
import net.fireimp.server.network.packets.world.PacketRequestSection;
import net.fireimp.server.network.packets.world.PacketWorldInfo;
import net.fireimp.server.network.player.NetPhase;
import net.fireimp.server.network.player.PlayerConnection;

import java.util.List;

public class LoginListener implements PacketListener {
    private final PlayerConnection connection;
    private final World world;

    public LoginListener(PlayerConnection connection, World world) {
        this.connection = connection;
        this.world = world;
    }

    @Override
    public List<NetPhase> getSupportedPhases() {
        return Lists.newArrayList(NetPhase.LOGIN);
    }

    @PacketIn
    public void onConnectRequest(PacketConnectRequest packet) {
        connection.sendPacket(new PacketContinueConnecting(connection.getPlayerId()));
    }

    @PacketIn
    public void onConnectResponse(PacketFinishLogin packet) {
        connection.sendPacket(new PacketWorldInfo(world.getWorldInfo()));
    }

    @PacketIn
    public void onRequestSection(PacketRequestSection packet) {
        // TODO: Send section
        System.out.println("Ready to send sections!");
    }
}
