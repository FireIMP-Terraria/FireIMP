package net.fireimp.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fireimp.server.network.Codec;
import net.fireimp.server.network.NetManager;

import java.util.regex.Pattern;

public class TerrariaServer {
    public static void main(String[] args) {
//        String data = "63:00:07:0e:60:00:00:00:07:00:19:08:07:85:0c:a1:01:d1:01:d9:02:36:e5:aa:05:07:4d:65:6c:6c:65:74:68:00:06:00:00:2a:01:00:00:02:03:00:00:84:16:59:be:00:17:08:00:00:ca:0f:00:00:00:19:00:00:05:01:02:00:33:0c:00:00:c6:0c:00:00:00:19:00:00:06:00:03:07:00:00:00:00:3f:2a:18:00:00:00:00:00:00:00:00:00:00";
//        ByteBuf buf = Unpooled.buffer();
//        for(String s : data.split(Pattern.quote(":"))) {
//            buf.writeByte((byte) Integer.parseInt(s, 16));
//        }
//        Codec codec = new Codec(buf);
//        System.out.println("Length: " + codec.readShort());
//        System.out.println("Id: " + codec.readByte());
//        System.out.println(codec.readInt()); // time
//        System.out.println(codec.readByte()); // day and moon
//        System.out.println(codec.readByte()); // moon phase
//        System.out.println(codec.readShort()); // maxTilesX
//        System.out.println(codec.readShort()); // maxTilesY
//        System.out.println(codec.readShort()); // spawnX
//        System.out.println(codec.readShort()); // spawnY
//        System.out.println(codec.readShort()); // surface
//        System.out.println(codec.readShort()); // rocks
//        System.out.println(codec.readInt()); // world id
//        System.out.println(codec.readString()); // world name
//        System.out.println(codec.readByte()); // moon type
//        System.out.println(codec.readByte()); // tree bg
//        System.out.println(codec.readByte()); // corruption bg
//        System.out.println(codec.readByte()); // jungle bg
//        System.out.println(codec.readByte()); // snow bg
//        System.out.println(codec.readByte()); // hallow bg
//        System.out.println(codec.readByte()); // crimson bg
//        System.out.println(codec.readByte()); // desert bg
//        System.out.println(codec.readByte()); // ocean bg
//        System.out.println(codec.readByte()); // ice back style
//        System.out.println(codec.readByte()); // jungle back style
//        System.out.println(codec.readByte()); // hell back style
//        System.out.println(codec.readFloat()); // wind
//        System.out.println(codec.readByte()); // cloud number
//        System.out.println(codec.readInt()); // tree 1
//        System.out.println(codec.readInt()); // tree 2
//        System.out.println(codec.readInt()); // tree 3
//        System.out.println(codec.readByte()); // tree style 1
//        System.out.println(codec.readByte()); // tree style 2
//        System.out.println(codec.readByte()); // tree style 3
//        System.out.println(codec.readByte()); // tree style 4
//        System.out.println(codec.readInt()); // cave back 1
//        System.out.println(codec.readInt()); // cave back 2
//        System.out.println(codec.readInt()); // cave back 3
//        System.out.println(codec.readByte()); // cave style 1
//        System.out.println(codec.readByte()); // cave style 2
//        System.out.println(codec.readByte()); // cave style 3
//        System.out.println(codec.readByte()); // cave style 4
//        System.out.println(codec.readFloat()); // rain
//        System.out.println(Integer.toBinaryString(codec.readByte())); // event info
//        System.out.println(Integer.toBinaryString(codec.readByte())); // event info
//        System.out.println(Integer.toBinaryString(codec.readByte())); // event info
//        System.out.println(Integer.toBinaryString(codec.readByte())); // event info
//        System.out.println(codec.readUnsignedByte()); // invasion
//        System.out.println(codec.readLong()); // lobby
//        System.out.println("bytes left: " + buf.readableBytes());

        NetManager netManager = new NetManager(25565);
        netManager.start();
//        while(true);
    }
}
