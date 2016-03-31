package net.fireimp.server.datatypes;

import lombok.Data;
import net.fireimp.server.entities.Direction;
import net.fireimp.server.util.Vec2;
import net.fireimp.server.world.World;

public class Location extends Vec2 {
    public static final Location ZERO = new Location(null, 0, 0, Direction.NONE);
    private Direction directionX;
    private Direction directionY;
    private World world;

    public Location(World world, double x, double y, Direction directionX) {
        this(world, x, y, directionX, Direction.NONE);
    }

    public Location(World world, double x, double y, Direction directionX, Direction directionY) {
        super(x, y);
        this.world = world;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Direction getDirectionX() {
        return directionX;
    }

    public Direction getDirectionY() {
        return directionY;
    }

    public void setDirectionX(Direction directionX) {
        this.directionX = directionX;
    }


    public void flipDirection() {
        directionX = directionX == Direction.LEFT ? Direction.RIGHT : Direction.LEFT;
    }
}
