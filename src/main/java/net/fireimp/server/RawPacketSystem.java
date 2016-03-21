package net.fireimp.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

import java.util.regex.Pattern;

/**
 * Load raw network data into packets.
 *
 * Used to find out vanilla network logic.
 */
public class RawPacketSystem {
    private static final String SECTION_PACKET_HEX = "8f:07:0a:01:75:57:bd:8b:1c:65:18:7f:bf:e6:9d:9d:dd:bd:bd:bd:e5:72:26:98:e2:42:62:32:b9:6c:24:06:14:34:16:c3:b2:c4:2b:82:04:82:70:04:6c:45:50:ae:b3:97:70:ff:c1:40:8a:74:5b:04:2c:2c:52:58:b8:16:36:c1:c3:c2:d2:52:2c:fc:0b:42:90:b4:eb:f3:7b:9e:e7:7d:67:36:e0:15:f7:ee:cc:3c:1f:bf:e7:f7:7c:cd:bc:1a:1a:33:b7:c6:fc:6e:9e:9a:ef:7f:fe:dc:15:07:e6:b5:69:7e:73:c5:dc:fc:65:5c:71:6a:16:f4:7f:41:bf:71:cf:98:03:3e:17:66:ce:cf:4e:f9:99:c8:1f:d0:3d:79:76:c0:cf:4e:e8:ff:09:69:67:bd:f3:fe:49:f7:ef:41:e7:dc:34:85:9e:4f:a0:2b:5a:a7:a6:f9:44:a5:03:90:9c:f3:dd:03:96:31:f0:73:0f:e7:29:eb:b2:ad:de:fd:03:5c:17:aa:fd:44:cf:2d:79:3a:bf:56:9f:23:5c:a7:53:e4:04:03:47:15:70:f7:85:81:ec:99:31:6e:fd:83:9f:d1:ff:5f:e8:7f:e3:5c:eb:0f:db:e6:c8:4f:db:e6:2e:c4:4d:82:98:4c:0d:fd:9d:56:84:2e:b8:fd:c7:f4:bc:b9:9a:42:a2:27:8b:36:90:aa:59:b4:ce:ad:60:e3:c6:a2:f5:24:6b:70:93:1f:db:e6:08:7e:61:08:0c:bc:ee:71:4a:f8:07:2c:53:40:73:0f:d6:81:eb:8c:d1:3d:05:ba:0b:1a:5e:45:02:4e:8d:4e:49:c1:df:6f:6b:4b:7a:6e:05:ff:04:ed:0a:db:71:b1:ad:17:6d:84:c7:5e:f4:43:c0:35:c9:97:25:55:27:20:07:1b:36:e6:e8:e7:f1:9f:05:ff:8e:14:c5:e1:8a:b0:68:b2:a4:6c:4e:c8:58:53:b2:7a:49:ea:83:fb:ab:da:09:02:e8:ba:f5:af:8e:cc:5f:62:92:80:cf:ad:2f:7a:ca:26:a3:2c:5d:2b:04:cc:99:d1:39:07:4d:7a:f4:28:2c:da:ea:78:63:85:01:a0:72:ed:62:85:7c:10:19:bb:4a:b1:38:85:74:11:ea:f8:7c:46:71:d4:cb:1a:31:04:05:81:58:dc:fa:47:46:40:f8:d5:e5:2d:ad:9f:91:d6:93:ba:1c:49:00:25:e9:da:c5:ca:0a:0d:22:44:c2:51:20:47:f1:34:83:27:17:9f:1b:73:6c:e8:c6:35:1c:cb:da:0b:70:4f:e1:d2:2d:72:6a:23:9f:35:dd:af:ca:86:f0:3c:67:b2:8f:3a:06:4f:52:e3:15:fd:ca:33:ee:d1:b7:5c:6c:73:ae:cc:05:55:66:42:79:b9:17:1a:91:71:7c:39:9a:ce:da:69:2a:9d:c2:3f:d4:74:0e:96:f5:50:b9:51:b8:aa:dc:5c:04:48:7a:10:38:80:99:3c:08:49:2a:33:5a:3e:a4:a8:89:16:82:6f:80:9f:2a:8f:09:3c:49:35:3e:40:4e:42:ce:45:02:58:d0:93:c6:aa:64:29:84:10:45:64:4a:1c:72:b2:76:fa:64:32:8b:70:87:c6:d8:d5:21:55:85:9a:10:02:1a:cb:a8:b4:6d:de:d7:22:70:5c:9a:81:04:bc:94:bb:91:b8:3f:10:df:73:f5:3d:83:f3:a0:81:2e:6b:22:5a:32:34:05:79:04:70:48:36:09:da:36:53:cb:ba:68:6e:6a:5f:7b:58:23:66:9d:44:59:49:59:b2:17:1e:36:64:40:09:85:4d:ee:69:56:a1:d4:46:d8:4e:c8:0b:8a:86:9e:d8:3c:11:2f:ba:f5:3f:28:50:97:5a:fc:5f:5c:59:e0:7a:c6:e9:3c:d3:d9:d4:58:75:1d:57:e0:e3:23:c8:2c:ba:94:13:80:83:66:a6:25:ed:95:1d:72:5d:d1:84:53:6f:a8:75:b7:fe:94:fa:8f:49:7b:97:02:76:ed:5b:16:99:8d:d1:b2:be:00:9c:7b:1a:b4:d7:26:28:c8:dc:16:fe:dc:85:28:fd:2f:73:ba:47:19:cb:a9:d2:a0:ca:a4:65:65:da:bf:b5:77:3c:23:02:a5:ef:a1:ff:e8:bc:21:a0:76:84:61:82:3e:5e:d6:fb:30:9a:f6:52:d6:75:ea:80:82:1d:68:3d:14:28:04:b8:39:4b:ed:e3:73:81:3e:d2:02:1d:89:65:82:bb:03:cb:41:7a:ae:19:e3:a6:70:fe:42:f3:70:c6:1c:3f:e3:7b:cf:24:90:72:da:d6:52:e4:85:0c:b6:95:18:ff:82:8c:83:8b:a1:e9:96:e4:69:02:3a:8e:eb:fd:40:55:10:d7:d7:03:17:c3:14:66:1e:d4:1b:8b:51:07:dc:16:e3:76:53:f1:ef:12:e3:c7:53:f9:53:58:b0:48:e5:9d:12:96:38:ba:4d:27:38:ab:7a:a3:82:41:af:ff:e0:59:f2:37:f6:04:46:3b:da:a3:3c:6c:31:a3:22:cf:19:c2:80:41:43:37:30:15:ad:6b:65:3b:60:a2:6c:8a:dc:52:28:76:84:5b:6a:b8:3b:92:93:5a:dd:cf:e9:84:ba:e0:f5:58:38:43:48:41:3a:d4:aa:43:9d:32:72:dc:56:dc:80:18:80:dc:e0:51:5c:ef:a8:ad:9b:9a:15:9c:a5:a8:81:c7:bb:bd:79:1b:64:fc:d0:04:87:6e:b4:b0:3e:16:44:47:4a:fb:6d:b5:32:64:9a:3e:24:b1:d4:e8:a5:b8:1b:a9:bb:5b:2a:78:6f:5b:10:74:2d:99:d2:a1:0a:7e:c3:02:03:71:73:0b:13:61:c8:29:09:3d:99:8f:35:0d:53:91:ba:a4:93:57:a1:91:2c:6a:97:f3:a4:5e:07:bd:d9:aa:a8:11:f3:44:ed:5d:92:3d:61:20:25:03:bb:14:5b:56:05:bc:5c:5e:a1:74:a2:1d:c7:9c:56:cc:8d:ba:87:2a:60:64:eb:e8:e1:2d:c5:34:f2:2c:45:f4:15:b6:99:d8:29:14:d6:ae:9e:51:88:36:9d:72:de:06:ea:bc:6c:56:c8:f0:c3:15:97:02:06:27:1b:ba:8a:58:77:64:11:02:b2:3c:d0:a4:27:ec:c4:f5:f5:ef:ac:91:39:9b:d6:e3:44:03:94:63:5f:81:60:8f:83:e8:42:6e:a7:b5:1e:3a:30:95:26:3e:36:16:24:48:8c:a8:5c:75:9a:37:4d:da:2f:8a:e2:9a:f2:45:4a:00:ec:ca:3b:9c:09:e4:4c:56:e0:2c:8b:ba:fc:6a:51:76:8b:72:a2:29:df:13:64:a5:5e:06:b9:f4:8a:db:22:05:ea:b8:db:74:ca:78:60:d8:4e:d8:52:49:f6:9b:b7:94:a6:f9:3a:1d:8c:93:90:ad:a3:cf:ef:13:ab:7a:99:c1:25:9c:ac:37:e8:6e:c6:0e:50:7e:b9:e9:6e:0f:b6:61:3b:f4:33:a5:47:cb:d2:eb:d3:d8:50:ce:5c:ce:19:8a:2a:76:1e:6e:60:1b:4f:22:bf:e7:e5:9e:cf:94:f9:ae:ec:c8:33:0a:ea:f2:b1:f9:89:ac:5c:7e:8c:83:da:a2:cb:99:0d:30:ae:09:05:96:4a:2e:ad:5e:fa:5e:09:44:40:cd:75:c7:7c:ab:ee:92:03:75:3a:9a:18:bb:d7:62:50:dc:b3:dc:78:4e:ad:1f:e6:57:38:db:8c:f3:9b:70:d5:9b:3d:05:6a:9d:f3:c0:11:9c:4b:04:e7:79:80:76:0d:22:89:85:e5:72:1b:5f:0f:bd:d7:ae:a1:63:a0:97:1c:33:72:9a:a9:1b:68:c3:8f:c5:36:c1:6b:82:be:74:76:59:96:8a:e6:75:96:5b:55:83:db:23:61:4c:87:91:6c:26:9a:6b:d8:f3:13:a9:a0:8d:66:ca:e2:a4:ae:d5:fa:b7:a1:ee:27:cc:2b:38:d7:25:a2:ec:2a:27:45:52:f5:22:74:3d:ce:06:a2:b5:a3:45:1b:18:7f:9a:f7:e3:fe:fb:b3:c9:53:40:62:d8:46:de:4d:bd:94:c6:21:8f:b7:43:ec:ce:97:76:b2:3e:82:f9:97:e3:fa:e5:7d:79:4d:64:02:ba:3c:b8:ae:e0:75:be:54:69:44:6a:02:9c:4a:53:ad:60:41:8c:a4:5a:cb:c3:55:8d:ed:37:ee:a2:1f:c9:12:4d:49:c8:5c:8f:75:12:0f:e5:45:40:14:9c:a4:34:f7:35:5e:1b:ee:c8:c0:85:99:71:bd:01:04:79:27:56:b2:ad:02:d1:dd:b1:2b:33:b4:ab:6e:db:03:1b:22:7f:d9:80:68:ce:de:aa:de:05:da:91:a6:72:57:56:ce:e8:41:fd:26:1c:bf:19:67:54:5e:48:1a:21:97:04:61:22:03:92:4e:66:9e:4f:5c:8f:a5:44:96:19:96:a2:2c:7a:fb:a2:ea:b6:52:d4:c5:90:4b:3e:45:92:92:a7:85:16:15:6a:54:33:fc:88:bf:2b:1d:a7:11:f3:b0:8a:eb:57:f2:8a:e2:82:4c:86:a9:ce:09:59:86:14:d2:c6:1d:6f:28:24:ff:bf:21:cd:24:84:89:d4:22:7e:f2:dc:f2:5f:e9:14:b3:e9:63:48:ca:b7:50:2b:65:57:ec:71:bb:f6:53:0b:74:79:d0:17:aa:a2:17:2c:13:23:4d:cc:f3:84:3e:59:29:b2:21:af:9a:e9:60:fa:a0:9e:3a:ad:d6:a9:f6:e1:14:15:f1:19:51:63:41:b8:64:6e:d6:19:1e:6a:a8:f9:0b:5e:4b:21:74:9f:28:e8:85:88:2a:9b:c8:9d:0d:a9:04:19:d2:69:64:f7:c6:4c:d4:d9:99:b2:5a:a4:29:28:b6:ab:d4:58:44:95:a2:49:1d:84:5e:f1:b2:74:52:39:90:27:af:4f:df:e1:0c:a5:3e:1e:52:30:fd:04:45:49:4e:4c:45:46:8a:b1:07:b7:9c:88:e6:a6:57:5f:85:66:a3:0f:d4:6e:03:c5:b7:b8:b0:19:b6:5f:43:fa:6f:4b:18:25:fc:f7:1f";
    private static final String ENTITY_PACKET_HEX = "1e:00:17:02:00:49:73:84:47:c7:51:15:46:b2:94:f1:bf:f1:c4:4c:3e:00:84:00:00:80:3f:37:00:ff";
    public void run() {
        try {
            decode(ENTITY_PACKET_HEX);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void decode(String data) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        for(String hex : data.split(Pattern.quote(":"))) {
            buf.writeByte((byte) Integer.parseInt(hex, 16));
        }
        Codec codec = new Codec(buf);
        short length = codec.readShort();
        byte id = codec.readByte();
        System.out.println("Packet(" + id + "): " + length);
        PacketType type = PacketType.getTypeById(id);
        NetworkPacket packet = (NetworkPacket) PacketType.getClassByType(type).newInstance();
        packet.decode(codec);
        System.out.println(packet);
        buf.release();
    }
}
