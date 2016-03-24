package net.fireimp.server.test;

import net.fireimp.server.network.packets.PacketType;
import org.junit.Assert;
import org.junit.Test;

public class PacketCoverageTest {
    @Test
    public void testPacketCoverage() {
        double coverage = 0;
        double total = 0;
        for(PacketType type : PacketType.values()) {
            switch (type) {
                case DEPRECATED:
                    continue;
                case SOCIAL_HANDSHAKE:
                    continue;
                case LOAD_NET_MODULE:
                    continue;
                case PLACEHOLDER:
                    continue;
            }
            Class<?> typeClass = PacketType.getClassByType(type);
            if (typeClass != null) {
                coverage += 1;
            }
            total += 1;
        }
        double percentage = (coverage/total) * 100;
        System.out.println("Packet coverage: " + percentage + "% (" + coverage + "/" + total + "}");
        Assert.assertTrue(true);

    }
}
