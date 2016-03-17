package net.fireimp.server.world;

import net.fireimp.server.network.Codec;
import net.fireimp.server.util.BitFlags;

/**
 * Stores metadata about a world instance
 * TODO: Document undocumented fields
 */
public class WorldInfo {
    /**
     * Current time in the world
     */
    private int time = 12000;
    /**
     * Describes day and moon appearance
     * BitFlags:
     * 1 - Currently daytime
     * 2 - Currently a Blood Moon
     * 4 - Currently an Eclipse
     */
    private byte dayMoonInfo = 1;

    /**
     * The current stage of the moon
     */
    private byte moonPhase = 7;
    private int maxTilesX =  6400;
    private int maxTilesY = 1800;
    /**
     * The x spawn location in tiles
     */
    private int spawnX = 3200; //Example: 3200
    /**
     * The y spawn location in tiles
     */
    private int spawnY = 320; //Example: 320
    /**
     * The layer the world defines as "surface". This means
     * surface mobs will spawn and a surface background will be used
     */
    private int surfaceLayer = 465; //Example: 465
    /**
     * The layer the world defines as "caverns".
     * This means cavernous mobs will spawn and a cavern background will be used
     */
    private int rockLayer = 729; //Example: 729
    private int worldId = (int) 1559098370; // Example: 1559098370
    /**
     * The name of this world
     */
    private String worldName = "FireIMP World"; //Example: "World 1"
    private byte moonType = 0; //Max: 32
    private byte treeBackground = 6; //Max: 8
    private byte corruptionBackground = 0; //Max: 8
    private byte jungleBackground = 0; //Max: 8
    private byte snowBackground = 42; //Max: 8
    private byte hallowBackground = 1; //Max: 8
    private byte crimsonBackground = 0; //Max: 8
    private byte desertBackground = 0; //Max: 8
    private byte oceanBackground = 2; //Max: 8
    private byte iceBackstyle = 3; //Max: 6
    private byte jungleBackstyle = 0; //Max: 6
    private byte hellBackstyle = 0; //Max: 6
    private float windSpeed = -1.7673623E-36f;
    /**
     * The number of clouds to spawn in the world
     */
    private byte cloudNumber = (byte) 0;
    /**
     * The X location of the first Living Wood
     */
    private int treeOneX = 2071;
    /**
     * The X location of the second Living Wood
     */
    private int treeTwoX = 4042;
    /**
     * The X location of the third Living Wood
     */
    private int treeThreeX = 6400;
    /**
     * The style of the first living wood
     * @max 6
     */
    private byte treeStyleOne = 5;
    /**
     * The style of the second living wood
     * @max 6
     */
    private byte treeStyleTwo = 1;
    /**
     * The style of the third living wood
     * @max 6
     */
    private byte treeStyleThree = 2;
    /**
     * The style of the fourth living wood
     * @max 6
     */
    private byte treeStyleFour = 0;
    private int caveBackOne = 3123; //Example: 3123
    private int caveBackTwo = 3270;
    private int caveBackThree = maxTilesX;
    private byte caveBackstyleOne = 6;
    private byte caveBackstyleTwo = 0;
    private byte caveBackstyleThree = 3;
    private byte caveBackstyleFour = 7;
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
    private BitFlags eventInfo = new BitFlags();
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
    private BitFlags eventInfoTwo = new BitFlags();
    /**
     * Unused, presumably for future content
     */
    private byte eventInfoThree;
    /**
     * Unused, presumably for future content
     */
    private byte eventInfoFour;
    /**
     * The type of invasion currently ongoing
     */
    private byte invasionType;
    private long lobbyId = 0L;

    public WorldInfo(String worldName) {
        this.worldName = worldName;
    }

    public void write(Codec codec) {
        codec.writeInt(time);
        codec.writeByte(dayMoonInfo);
        codec.writeByte(moonPhase);
        codec.writeShort(maxTilesX);
        codec.writeShort(maxTilesY);
        codec.writeShort(spawnX);
        codec.writeShort(spawnY);
        codec.writeShort(surfaceLayer);
        codec.writeShort(rockLayer);
        codec.writeInt(worldId);
        codec.writeString(worldName);
        codec.writeByte(moonType);
        codec.writeByte(treeBackground);
        codec.writeByte(corruptionBackground);
        codec.writeByte(jungleBackground);
        codec.writeByte(snowBackground);
        codec.writeByte(hallowBackground);
        codec.writeByte(crimsonBackground);
        codec.writeByte(desertBackground);
        codec.writeByte(oceanBackground);
        codec.writeByte(iceBackstyle);
        codec.writeByte(jungleBackstyle);
        codec.writeByte(hellBackstyle);
        codec.writeFloat(windSpeed);
        codec.writeByte(cloudNumber);
        codec.writeInt(treeOneX);
        codec.writeInt(treeTwoX);
        codec.writeInt(treeThreeX);
        codec.writeByte(treeStyleOne);
        codec.writeByte(treeStyleTwo);
        codec.writeByte(treeStyleThree);
        codec.writeByte(treeStyleFour);
        codec.writeInt(caveBackOne);
        codec.writeInt(caveBackTwo);
        codec.writeInt(caveBackThree);
        codec.writeByte(caveBackstyleOne);
        codec.writeByte(caveBackstyleTwo);
        codec.writeByte(caveBackstyleThree);
        codec.writeByte(caveBackstyleFour);
        codec.writeFloat(rain);
        codec.writeByte(eventInfo.getValue());
        codec.writeByte(eventInfoTwo.getValue());
        codec.writeByte(eventInfoThree);
        codec.writeByte(eventInfoFour);
        codec.writeByte(invasionType);
        codec.writeLong(lobbyId);
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

    public int getMaxTilesX() {
        return maxTilesX;
    }

    public void setMaxTilesX(int maxTilesX) {
        this.maxTilesX = maxTilesX;
    }

    public int getMaxTilesY() {
        return maxTilesY;
    }

    public void setMaxTilesY(int maxTilesY) {
        this.maxTilesY = maxTilesY;
    }

    public int getSpawnX() {
        return spawnX;
    }

    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }

    public int getSpawnY() {
        return spawnY;
    }

    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }

    public int getSurfaceLayer() {
        return surfaceLayer;
    }

    public void setSurfaceLayer(int surfaceLayer) {
        this.surfaceLayer = surfaceLayer;
    }

    public int getRockLayer() {
        return rockLayer;
    }

    public void setRockLayer(int rockLayer) {
        this.rockLayer = rockLayer;
    }

    public int getWorldId() {
        return worldId;
    }

    public void setWorldId(int worldId) {
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

    public BitFlags getEventInfo() {
        return eventInfo;
    }

    public BitFlags getEventInfoTwo() {
        return eventInfoTwo;
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
