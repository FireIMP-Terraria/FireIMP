package net.fireimp.server.entities;

import net.fireimp.server.datatypes.Location;
import net.fireimp.server.datatypes.Vec2;
import net.fireimp.server.datatypes.enums.EntityType;

public class Entity {
    public static int LAST_ENTITY_ID = 0;

    private final int id;
    private EntityType entityType;
    private byte targetId = -1;
    private Location location;
    private Vec2 velocity = new Vec2(0, 0);
    private int health;
    private int maxHealth;

    public Entity(EntityType entityType) {
        this.id = LAST_ENTITY_ID++;
        this.entityType = entityType;
        setMaxHealth(200);
        setHealth(maxHealth);
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(double x, double y) {
        this.location = new Location(x, y, Direction.RIGHT);
    }


    public Vec2 getVelocity() {
        return velocity;
    }

    public void setVelocity(double x, double y) {
        this.velocity = new Vec2(x, y);
    }

    public int getId() {
        return id;
    }

    public void setTargetId(int targetId) {
        this.targetId = (byte) targetId;
    }

    public int getTargetId() {
        return targetId;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
