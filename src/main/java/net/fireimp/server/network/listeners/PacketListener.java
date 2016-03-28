package net.fireimp.server.network.listeners;

import net.fireimp.server.network.player.NetPhase;

import java.util.List;

public interface PacketListener {

    List<NetPhase> getSupportedPhases();
}
