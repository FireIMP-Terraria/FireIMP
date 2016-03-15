package net.fireimp.server.network.packets.login;

import net.fireimp.server.network.Codec;
import net.fireimp.server.network.packets.NetworkPacket;
import net.fireimp.server.network.packets.PacketType;

public class PacketWorldInfo extends NetworkPacket {
    public PacketWorldInfo() {
        super(PacketType.WORLD_INFO);
    }

    @Override
    public void encode(Codec codec) {
        codec.writeInt(24590); // time 0
        codec.writeByte(0); // day bitflag
        codec.writeByte(7); // moon phase
        codec.writeShort(6400); //max tiles x
        codec.writeShort(1800); // max tiles z
        codec.writeShort(3205); // spawn x
        codec.writeShort(417); // spawn y
        codec.writeShort(465); // surface layer
        codec.writeShort(729); // rock layer
        codec.writeInt(95085879); // world id
        codec.writeString("FireIMP"); // world name
        codec.writeByte(0); // moon type
        codec.writeByte(6); // tree background
        codec.writeByte(0); // corruption background
        codec.writeByte(0); // jungle background
        codec.writeByte(42); // snow background
        codec.writeByte(1); // hallow background
        codec.writeByte(0); // crimson background
        codec.writeByte(0); // desert background
        codec.writeByte(2); // ocean background
        codec.writeByte(3); // ice back style
        codec.writeByte(0); // jungle back style
        codec.writeByte(0); // hell back style
        codec.writeFloat(-1.7673623E-36F); // wind speed
        codec.writeByte(0); // cloud number
        codec.writeInt(2071); // tree 1
        codec.writeInt(4042); // tree 2
        codec.writeInt(6400); // tree 3
        codec.writeByte(5); // tree style 1
        codec.writeByte(1); // tree style 2
        codec.writeByte(2); // tree style 3
        codec.writeByte(0); // tree style 4
        codec.writeInt(3123); // cave back 1
        codec.writeInt(3270); // cave back 2
        codec.writeInt(6400); // cave back 3
        codec.writeByte(6); // cave back style 1
        codec.writeByte(0); // "             " 2
        codec.writeByte(3); // "             " 3
        codec.writeByte(7); // "             " 4
        codec.writeFloat(0.0F); // rain
        codec.writeByte(0x3F);
        codec.writeByte(0x2A);
        codec.writeByte(0x18);
        codec.writeByte(0);
        codec.writeByte(0);
        codec.writeLong(0L);
    }
}
