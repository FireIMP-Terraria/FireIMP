package net.fireimp.server.datatypes.tiles;

import net.fireimp.server.datatypes.Bitflags;
import net.fireimp.server.datatypes.StreamObject;
import net.fireimp.server.datatypes.enums.Liquid;
import net.fireimp.server.datatypes.enums.TileType;
import net.fireimp.server.datatypes.enums.WallType;
import net.fireimp.server.network.Codec;

public class TileSection implements StreamObject {
    /**
     * The first flag byte.
     * Bitmasks:
     * 1 = Flag2 exists
     * 2 = Active
     * 4 = Wall
     * 8 = LiquidPresent
     * 16 = Lava
     * 32 = Type is a short
     * 64 = RepeatValuePresent
     * 128 = ExtraRepeatValue
     */
    private Bitflags byteOne = new Bitflags();
    /**
     * The second flag byte.
     * Bitmasks:
     * 1 = Flag2 exists
     * 2 = Active
     * 4 = Wall
     * 8 = LiquidPresent
     * 16 = Lava
     * 32 = Type is a short
     * 64 = RepeatValuePresent
     * 128 = ExtraRepeatValue
     */
    private Bitflags byteTwo = new Bitflags();
    /**
     * The third flag byte.
     * Bitmasks:
     * 1 = Flag2 exists
     * 2 = Active
     * 4 = Wall
     * 8 = LiquidPresent
     * 16 = Lava
     * 32 = Type is a short
     * 64 = RepeatValuePresent
     * 128 = ExtraRepeatValue
     */
    private Bitflags byteThree = new Bitflags();
    private boolean isActive;
    private boolean isWall;
    private boolean isLiquidPresent;
    private boolean isLavaPresent;
    private boolean isShort;
    private boolean repeatValuePresent;
    private boolean extraRepeatValue;
    private boolean hasWireOne;
    private boolean hasWireTwo;
    private boolean hasWireThree;
    private boolean isHalfbrickOrSlope;
    private boolean slopeVal1;
    private boolean slopeVal2;
    private boolean isActuator;
    private boolean isInActive;
    private boolean isColored;
    private boolean wallIsColored;
    private byte color = 0;
    private byte wallColor = 0;
    private TileType type;
    private WallType wallType;
    private short repeat;

    public TileSection(TileType tileType, WallType wallType) {
        this(tileType, (short) 0, wallType);
    }

    public TileSection(TileType tileType) {
        this(tileType, (short) 0);
    }

    public TileSection(TileType tileType, short repeat, WallType wallType) {
        this(tileType, repeat);
        this.wallType = wallType;
    }

    public TileSection(TileType tileType, short repeat) {
        this.repeat = repeat;
        this.type = tileType;
        this.isShort = tileType.ordinal() >= 255;
        this.repeatValuePresent = repeat > 0;
        this.extraRepeatValue = repeat >= 255;
    }

    @Override
    public void write(Codec codec) {
        updateFlags();
        codec.writeByte(byteOne.getValue());
        codec.writeByte(byteTwo.getValue());
        codec.writeByte(byteThree.getValue());
        if(isActive) {
            if(isShort) {
                codec.writeShort(getType().ordinal());
            } else {
                codec.writeByte(getType().ordinal());
            }
        }
        //TODO: Implement frame importance
        if(isColored) {
            codec.writeByte(color);
        }
        if(isWall) {
            codec.writeByte(wallType.ordinal());
            if(wallIsColored) {
                codec.writeByte(wallColor);
            }
        }
        if(isLiquidPresent) {
            if(isLavaPresent) {
                codec.writeByte(Liquid.HONEY.ordinal());
            } else {
                codec.writeByte(Liquid.WATER.ordinal());
            }
        } else if(isLavaPresent) {
            codec.writeByte(Liquid.LAVA.ordinal());
        }
        if(hasWireOne) {
            codec.writeByte(1);
        }
        if(hasWireTwo) {
            codec.writeByte(1);
        }
        if(hasWireThree) {
            codec.writeByte(1);
        }
        if(repeatValuePresent) {
            if(extraRepeatValue) {
                codec.writeShort(repeat);
            } else {
                codec.writeByte(repeat);
            }
        }
    }

    @Override
    public void read(Codec codec) {

    }

    private void updateFlags() {
        byteThree.setFlag(2, isActuator);
        byteThree.setFlag(4, isInActive);
        byteThree.setFlag(8, isColored);
        byteThree.setFlag(16, wallIsColored);

        byteTwo.setFlag(1, byteThree.getValue() != 0);
        byteTwo.setFlag(2, hasWireOne);
        byteTwo.setFlag(4, hasWireTwo);
        byteTwo.setFlag(8, hasWireThree);
        byteTwo.setFlag(16, isHalfbrickOrSlope);
        byteTwo.setFlag(32, slopeVal1);
        byteTwo.setFlag(64, slopeVal2);

        byteOne.setFlag(1, byteTwo.getValue() != 0);
        byteOne.setFlag(2, isActive);
        byteOne.setFlag(4, isWall);
        byteOne.setFlag(8, isLiquidPresent);
        byteOne.setFlag(16, isLavaPresent);
        byteOne.setFlag(32, isShort);
        byteOne.setFlag(64, repeatValuePresent);
        byteOne.setFlag(128, extraRepeatValue);

    }

    public void setColor(byte color) {
        this.color = color;
    }

    public byte getColor() {
        return color;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isWall() {
        return isWall;
    }

    public void setWall(boolean wall) {
        isWall = wall;
    }

    public boolean isLiquidPresent() {
        return isLiquidPresent;
    }

    public void setLiquidPresent(boolean liquidPresent) {
        isLiquidPresent = liquidPresent;
    }

    public boolean isLavaPresent() {
        return isLavaPresent;
    }

    public void setLavaPresent(boolean lavaPresent) {
        isLavaPresent = lavaPresent;
    }

    public boolean hasWireOne() {
        return hasWireOne;
    }

    public void setHasWireOne(boolean hasWireOne) {
        this.hasWireOne = hasWireOne;
    }

    public boolean hasWireTwo() {
        return hasWireTwo;
    }

    public void setHasWireTwo(boolean hasWireTwo) {
        this.hasWireTwo = hasWireTwo;
    }

    public boolean hasWireThree() {
        return hasWireThree;
    }

    public void setHasWireThree(boolean hasWireThree) {
        this.hasWireThree = hasWireThree;
    }

    public boolean isHalfbrickOrSlope() {
        return isHalfbrickOrSlope;
    }

    public void setHalfbrickOrSlope(boolean halfbrickOrSlope) {
        isHalfbrickOrSlope = halfbrickOrSlope;
    }

    public boolean isSlopeVal1() {
        return slopeVal1;
    }

    public void setSlopeVal1(boolean slopeVal1) {
        this.slopeVal1 = slopeVal1;
    }

    public boolean isSlopeVal2() {
        return slopeVal2;
    }

    public void setSlopeVal2(boolean slopeVal2) {
        this.slopeVal2 = slopeVal2;
    }

    public boolean isActuator() {
        return isActuator;
    }

    public void setActuator(boolean actuator) {
        isActuator = actuator;
    }

    public boolean isInActive() {
        return isInActive;
    }

    public void setInActive(boolean inActive) {
        isInActive = inActive;
    }

    public boolean isColored() {
        return isColored;
    }

    public void setColored(boolean colored) {
        isColored = colored;
    }

    public boolean getWallIsColored() {
        return wallIsColored;
    }

    public void setWallIsColored(boolean wallIsColored) {
        this.wallIsColored = wallIsColored;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public WallType getWallType() {
        return wallType;
    }

    public void setWallType(WallType wallType) {
        this.wallType = wallType;
    }

    public byte getWallColor() {
        return wallColor;
    }

    public void setWallColor(byte wallColor) {
        this.wallColor = wallColor;
    }

    public short getRepeat() {
        return repeat;
    }

    public void setRepeat(short repeat) {
        this.repeat = repeat;
    }
}
