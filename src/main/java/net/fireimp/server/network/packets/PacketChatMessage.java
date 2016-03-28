package net.fireimp.server.network.packets;

import net.fireimp.server.datatypes.chat.MessageSection;
import net.fireimp.server.network.Codec;

import java.awt.*;

public class PacketChatMessage extends NetworkPacket {
    private MessageSection message;
    private byte playerId;
    private Color color;

    public PacketChatMessage() {
        super(PacketType.CHAT_MESSAGE);
    }

    public PacketChatMessage(MessageSection message, byte playerId, Color color) {
        this();
        this.message = message;
        this.playerId = playerId;
        this.color = color;
    }

    public PacketChatMessage(MessageSection message, Color color) {
        this(message, (byte) 255, color);
    }

    @Override
    public void encode(Codec codec) {
        codec.writeByte(playerId);
        codec.writeByte(color.getRed());
        codec.writeByte(color.getBlue());
        codec.writeByte(color.getGreen());
        codec.writeString(message.getMessage());
    }

    @Override
    public void decode(Codec codec) {
        this.playerId = codec.readByte();
        color = new Color(codec.readByte(), codec.readByte(), codec.readByte());
        message = new MessageSection(codec.readString());
        System.out.println(message.getMessage());
    }
}
