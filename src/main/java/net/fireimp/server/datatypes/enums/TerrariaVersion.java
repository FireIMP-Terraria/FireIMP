package net.fireimp.server.datatypes.enums;

public enum TerrariaVersion {
    ONE_THREE_ZERO_SEVEN("1.3.0.7", 155),
    ONE_THREE_ZERO_EIGHT("1.3.0.8", 156);
    public static TerrariaVersion LATEST = ONE_THREE_ZERO_EIGHT;

    private final String friendlyName;
    private final int protocolVersion;
    TerrariaVersion(String friendlyName, int protocolVersion) {
        this.friendlyName = friendlyName;
        this.protocolVersion = protocolVersion;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getFriendlyName() {
        return friendlyName;
    }
}
