package net.fireimp.server.datatypes;

import net.fireimp.server.entities.Direction;

public class Location extends Vec2 {
    private Direction directionX;
    private Direction directionY;

    public Location(double x, double y, Direction directionX) {
        this(x, y, directionX, Direction.UP);
    }

    public Location(double x, double y, Direction directionX, Direction directionY) {
        super(x, y);
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public Direction getDirectionX() {
        return directionX;
    }

    public Direction getDirectionY() {
        return directionY;
    }
}
