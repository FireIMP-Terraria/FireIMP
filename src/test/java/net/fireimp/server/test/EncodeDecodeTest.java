package net.fireimp.server.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.login.PacketConnectRequest;
import net.fireimp.server.network.packets.login.PacketKickPlayer;
import org.junit.Assert;
import org.junit.Test;

public class EncodeDecodeTest {
    @Test
    public void testEncode() {
        ByteBuf buf = Unpooled.buffer();
        Codec codec = new Codec(buf);
        new PacketKickPlayer("Test").encode(codec);
        Assert.assertEquals("Failed to encode packet!", codec.readString(), "Test");
    }

    @Test
    public void testDecode() {
        ByteBuf buf = Unpooled.buffer();
        Codec codec = new Codec(buf);
        codec.writeString("Test");
        PacketConnectRequest connectRequest = new PacketConnectRequest();
        connectRequest.decode(codec);
        Assert.assertEquals("Failed to decode packet!", connectRequest.getVersion(), "Test");
    }

}
