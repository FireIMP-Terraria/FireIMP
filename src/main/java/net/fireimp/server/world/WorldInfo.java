package net.fireimp.server.world;

import lombok.Data;
import lombok.Getter;
import net.fireimp.server.util.Vec2;
import net.fireimp.server.network.Codec;

/**
 * Stores metadata about a world instance
 * TODO: Document undocumented fields
 */
@Data
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
     * The spawn location in tiles
     */
    private Vec2 spawnLocation = new Vec2(3200, 320);

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
    private int caveBackThree = 5000;
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
    private byte eventInfo = 0;
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
    private byte eventInfoTwo = 0;
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
        codec.writeShort((int) spawnLocation.getX());
        codec.writeShort((int) spawnLocation.getY());
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
        codec.writeByte(eventInfo);
        codec.writeByte(eventInfoTwo);
        codec.writeByte(eventInfoThree);
        codec.writeByte(eventInfoFour);
        codec.writeByte(invasionType);
        codec.writeLong(lobbyId);
    }
}
