package net.fireimp.server.datatypes;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;

/**
 * Stores metadata about a world instance
 * TODO: Document undocumented fields
 */
public class WorldInfo {
    /**
     * Current time in the world
     */
    private int time;
    /**
     * Describes day and moon appearance
     * BitFlags:
     * 1 - Currently daytime
     * 2 - Currently a Blood Moon
     * 4 - Currently an Eclipse
     */
    private byte dayMoonInfo = 0;

    /**
     * The current stage of the moon
     */
    private byte moonPhase = 1;
    private short maxTilesX =  8401;
    private short maxTilesY = 2401;
    /**
     * The x spawn location in tiles
     */
    private short spawnX = 28167;
    /**
     * The y spawn location in tiles
     */
    private short spawnY = 17420;
    /**
     * The layer the world defines as "surface". This means
     * surface mobs will spawn and a surface background will be used
     */
    private short surfaceLayer = -17919;
    /**
     * The layer the world defines as "caverns".
     * This means cavernous mobs will spawn and a cavern background will be used
     */
    private short rockLayer = -15871;
    private short worldId = (short) 1559098370;
    /**
     * The name of this world
     */
    private String worldName;
    private byte moonType = 32;
    private byte treeBackground = 0;
    private byte corruptionBackground = 0;
    private byte jungleBackground = 0;
    private byte snowBackground = 0;
    private byte hallowBackground = 0;
    private byte crimsonBackground = 0;
    private byte desertBackground = 0;
    private byte oceanBackground = 0;
    private byte iceBackstyle = 1;
    private byte jungleBackstyle = 1;
    private byte hellBackstyle = 2;
    private float windSpeed = 0f;
    private byte cloudNumber = (byte) 200;
    private int treeOneX = 10;
    private int treeTwoX = -10;
    private int treeThreeX = 0;
    private byte treeStyleOne = 1;
    private byte treeStyleTwo = 2;
    private byte treeStyleThree = 2;
    private byte treeStyleFour = 0;
    private int caveBackOne = 200;
    private int caveBackTwo = 200;
    private int caveBackThree = maxTilesX;
    private byte caveBackstyleOne = 3;
    private byte caveBackstyleTwo = 5;
    private byte caveBackstyleThree = 2;
    private byte caveBackstyleFour = 3;
    private float rain = 0f;
    /**
     * Describes events that have occurred in this world
     * Bitflags:
     * 1 - Shadow Orb Smashed
     * 2 - Defeated Eye of Cthulu
     * 4 - Defeated Eater of Worlds or Brain of Cthulu
     * 8 - Defeated Skeletron
     * 16 - Hard Mode unlocked
     * 32 - Downed Clown
     * 64 - Server Side Characters enabled
     * 128 - Plantera killed
     */
    private byte eventInfo;
    /**
     * Describes events that have occurred in this world
     * Bitflags:
     * 1 - First hardmode boss beaten(Twins?)
     * 2 - Second hardmode boss beaten(Destroyer?)
     * 4 - Defeated Skeletron Prime
     * 8 - Defeated any hard mode boss
     * 16 - Cloud BG(?)
     * 32 - Crimson instead of corruption
     * 64 - Pumpkin moon
     * 128 - Snow moon
     */
    private byte eventInfoTwo;
    /**
     * Unused, presumably for future content
     */
    private byte eventInfoThree;
    /**
     * Unused, presumably for future content
     */
    private byte eventInfoFour;
    private byte invasionType;
    private long lobbyId;

    public WorldInfo(String worldName) {
        this.worldName = worldName;
    }

    public void writeToBuffer(ByteBuf buffer) {
        buffer.writeIntLE(time);
        buffer.writeByte(dayMoonInfo);
        buffer.writeByte(moonPhase);
        buffer.writeShortLE(maxTilesX);
        buffer.writeShortLE(maxTilesY);
        buffer.writeShortLE(spawnX);
        buffer.writeShortLE(spawnY);
        buffer.writeShortLE(surfaceLayer);
        buffer.writeShortLE(rockLayer);
        buffer.writeIntLE(worldId);
        SevenBitInt length = SevenBitInt.fromInt(worldName.length());
        buffer.writeBytes(length.getValue());
        buffer.writeBytes(worldName.getBytes(Charset.forName("UTF-8")));
        buffer.writeByte(moonType);
        buffer.writeByte(treeBackground);
        buffer.writeByte(corruptionBackground);
        buffer.writeByte(jungleBackground);
        buffer.writeByte(snowBackground);
        buffer.writeByte(hallowBackground);
        buffer.writeByte(crimsonBackground);
        buffer.writeByte(desertBackground);
        buffer.writeByte(oceanBackground);
        buffer.writeByte(iceBackstyle);
        buffer.writeByte(jungleBackstyle);
        buffer.writeByte(hellBackstyle);
        buffer.writeFloat(windSpeed);
        buffer.writeByte(cloudNumber);
        buffer.writeIntLE(treeOneX);
        buffer.writeIntLE(treeTwoX);
        buffer.writeIntLE(treeThreeX);
        buffer.writeByte(treeStyleOne);
        buffer.writeByte(treeStyleTwo);
        buffer.writeByte(treeStyleThree);
        buffer.writeByte(treeStyleFour);
        buffer.writeIntLE(caveBackOne);
        buffer.writeIntLE(caveBackTwo);
        buffer.writeIntLE(caveBackThree);
        buffer.writeByte(caveBackstyleOne);
        buffer.writeByte(caveBackstyleTwo);
        buffer.writeByte(caveBackstyleThree);
        buffer.writeByte(caveBackstyleFour);
        buffer.writeFloat(rain);
        buffer.writeByte(eventInfo);
        buffer.writeByte(eventInfoTwo);
        buffer.writeByte(eventInfoThree);
        buffer.writeByte(eventInfoFour);
        buffer.writeByte(invasionType);
        buffer.writeLongLE(lobbyId);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public byte getDayMoonInfo() {
        return dayMoonInfo;
    }

    public void setDayMoonInfo(byte dayMoonInfo) {
        this.dayMoonInfo = dayMoonInfo;
    }

    public byte getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(byte moonPhase) {
        this.moonPhase = moonPhase;
    }

    public short getMaxTilesX() {
        return maxTilesX;
    }

    public void setMaxTilesX(short maxTilesX) {
        this.maxTilesX = maxTilesX;
    }

    public short getMaxTilesY() {
        return maxTilesY;
    }

    public void setMaxTilesY(short maxTilesY) {
        this.maxTilesY = maxTilesY;
    }

    public short getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(short spawnX) {
        this.spawnX = spawnX;
    }

    public short getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(short spawnY) {
        this.spawnY = spawnY;
    }

    public short getSurfaceLayer() {
        return surfaceLayer;
    }

    public void setSurfaceLayer(short surfaceLayer) {
        this.surfaceLayer = surfaceLayer;
    }

    public short getRockLayer() {
        return rockLayer;
    }

    public void setRockLayer(short rockLayer) {
        this.rockLayer = rockLayer;
    }

    public short getWorldId() {
        return worldId;
    }

    public void setWorldId(short worldId) {
        this.worldId = worldId;
    }

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    public byte getMoonType() {
        return moonType;
    }

    public void setMoonType(byte moonType) {
        this.moonType = moonType;
    }

    public byte getTreeBackground() {
        return treeBackground;
    }

    public void setTreeBackground(byte treeBackground) {
        this.treeBackground = treeBackground;
    }

    public byte getCorruptionBackground() {
        return corruptionBackground;
    }

    public void setCorruptionBackground(byte corruptionBackground) {
        this.corruptionBackground = corruptionBackground;
    }

    public byte getJungleBackground() {
        return jungleBackground;
    }

    public void setJungleBackground(byte jungleBackground) {
        this.jungleBackground = jungleBackground;
    }

    public byte getSnowBackground() {
        return snowBackground;
    }

    public void setSnowBackground(byte snowBackground) {
        this.snowBackground = snowBackground;
    }

    public byte getHallowBackground() {
        return hallowBackground;
    }

    public void setHallowBackground(byte hallowBackground) {
        this.hallowBackground = hallowBackground;
    }

    public byte getCrimsonBackground() {
        return crimsonBackground;
    }

    public void setCrimsonBackground(byte crimsonBackground) {
        this.crimsonBackground = crimsonBackground;
    }

    public byte getDesertBackground() {
        return desertBackground;
    }

    public void setDesertBackground(byte desertBackground) {
        this.desertBackground = desertBackground;
    }

    public byte getOceanBackground() {
        return oceanBackground;
    }

    public void setOceanBackground(byte oceanBackground) {
        this.oceanBackground = oceanBackground;
    }

    public byte getIceBackstyle() {
        return iceBackstyle;
    }

    public void setIceBackstyle(byte iceBackstyle) {
        this.iceBackstyle = iceBackstyle;
    }

    public byte getJungleBackstyle() {
        return jungleBackstyle;
    }

    public void setJungleBackstyle(byte jungleBackstyle) {
        this.jungleBackstyle = jungleBackstyle;
    }

    public byte getHellBackstyle() {
        return hellBackstyle;
    }

    public void setHellBackstyle(byte hellBackstyle) {
        this.hellBackstyle = hellBackstyle;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public byte getCloudNumber() {
        return cloudNumber;
    }

    public void setCloudNumber(byte cloudNumber) {
        this.cloudNumber = cloudNumber;
    }

    public int getTreeOneX() {
        return treeOneX;
    }

    public void setTreeOneX(int treeOneX) {
        this.treeOneX = treeOneX;
    }

    public int getTreeTwoX() {
        return treeTwoX;
    }

    public void setTreeTwoX(int treeTwoX) {
        this.treeTwoX = treeTwoX;
    }

    public int getTreeThreeX() {
        return treeThreeX;
    }

    public void setTreeThreeX(int treeThreeX) {
        this.treeThreeX = treeThreeX;
    }

    public byte getTreeStyleOne() {
        return treeStyleOne;
    }

    public void setTreeStyleOne(byte treeStyleOne) {
        this.treeStyleOne = treeStyleOne;
    }

    public byte getTreeStyleTwo() {
        return treeStyleTwo;
    }

    public void setTreeStyleTwo(byte treeStyleTwo) {
        this.treeStyleTwo = treeStyleTwo;
    }

    public byte getTreeStyleThree() {
        return treeStyleThree;
    }

    public void setTreeStyleThree(byte treeStyleThree) {
        this.treeStyleThree = treeStyleThree;
    }

    public byte getTreeStyleFour() {
        return treeStyleFour;
    }

    public void setTreeStyleFour(byte treeStyleFour) {
        this.treeStyleFour = treeStyleFour;
    }

    public int getCaveBackOne() {
        return caveBackOne;
    }

    public void setCaveBackOne(int caveBackOne) {
        this.caveBackOne = caveBackOne;
    }

    public int getCaveBackTwo() {
        return caveBackTwo;
    }

    public void setCaveBackTwo(int caveBackTwo) {
        this.caveBackTwo = caveBackTwo;
    }

    public int getCaveBackThree() {
        return caveBackThree;
    }

    public void setCaveBackThree(int caveBackThree) {
        this.caveBackThree = caveBackThree;
    }

    public byte getCaveBackstyleOne() {
        return caveBackstyleOne;
    }

    public void setCaveBackstyleOne(byte caveBackstyleOne) {
        this.caveBackstyleOne = caveBackstyleOne;
    }

    public byte getCaveBackstyleTwo() {
        return caveBackstyleTwo;
    }

    public void setCaveBackstyleTwo(byte caveBackstyleTwo) {
        this.caveBackstyleTwo = caveBackstyleTwo;
    }

    public byte getCaveBackstyleThree() {
        return caveBackstyleThree;
    }

    public void setCaveBackstyleThree(byte caveBackstyleThree) {
        this.caveBackstyleThree = caveBackstyleThree;
    }

    public byte getCaveBackstyleFour() {
        return caveBackstyleFour;
    }

    public void setCaveBackstyleFour(byte caveBackstyleFour) {
        this.caveBackstyleFour = caveBackstyleFour;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public byte getEventInfo() {
        return eventInfo;
    }

    public void setEventInfo(byte eventInfo) {
        this.eventInfo = eventInfo;
    }

    public byte getEventInfoTwo() {
        return eventInfoTwo;
    }

    public void setEventInfoTwo(byte eventInfoTwo) {
        this.eventInfoTwo = eventInfoTwo;
    }

    public byte getEventInfoThree() {
        return eventInfoThree;
    }

    public void setEventInfoThree(byte eventInfoThree) {
        this.eventInfoThree = eventInfoThree;
    }

    public byte getEventInfoFour() {
        return eventInfoFour;
    }

    public void setEventInfoFour(byte eventInfoFour) {
        this.eventInfoFour = eventInfoFour;
    }

    public byte getInvasionType() {
        return invasionType;
    }

    public void setInvasionType(byte invasionType) {
        this.invasionType = invasionType;
    }

    public long getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(long lobbyId) {
        this.lobbyId = lobbyId;
    }
}
