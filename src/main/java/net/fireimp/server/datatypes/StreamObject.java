package net.fireimp.server.datatypes;

import net.fireimp.server.network.Codec;

public interface StreamObject {
    void write(Codec codec);
    void read(Codec codec);
}
