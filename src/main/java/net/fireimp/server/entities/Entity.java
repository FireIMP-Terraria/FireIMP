package net.fireimp.server.entities;

import lombok.Data;
import net.fireimp.server.TerrariaServer;
import net.fireimp.server.datatypes.Location;
import net.fireimp.server.entities.ai.AIStyle;
import net.fireimp.server.network.packets.entity.PacketEntityUpdate;
import net.fireimp.server.network.player.PlayerConnection;
import net.fireimp.server.util.Vec2;
import net.fireimp.server.datatypes.enums.EntityType;
import net.fireimp.server.world.World;

@Data
public class Entity {
    public static int LAST_ENTITY_ID = 0;

    private int id;
    private EntityType entityType;
    private byte targetId = Byte.MAX_VALUE;
    private Location location;
    private Vec2 velocity = new Vec2(0, 0);
    private int health;
    private int maxHealth;
    private float dropValue = 3f;
    private float scale = 1f;
    private boolean needsNetUpdate = true;
    private float[] aiFloats = new float[4]; //Nobody seems to know what the hell this does.
    private AIStyle aiStyle;

    public Entity() {}

    protected Entity(EntityType entityType) {
        this.id = LAST_ENTITY_ID++;
        this.entityType = entityType;
        setMaxHealth(200);
        setHealth(maxHealth);
    }

    public void update(int tick) {
        aiStyle.applyToEntity(this);
        if(needsNetUpdate) {
            for(Player player : location.getWorld().getPlayers()) {
                sendUpdatePacket(player.getNetworkHandle());
            }
            needsNetUpdate = false;
        }
    }

    private void sendUpdatePacket(PlayerConnection playerConnection) {
        PacketEntityUpdate updatePacket = new PacketEntityUpdate(this);
        playerConnection.sendPacket(updatePacket);
    }

    public void setLocation(double x, double y) {
        World world;
        if(this.location != null && this.location.getWorld() != null) {
            world = this.location.getWorld();
        } else {
            world = TerrariaServer.getInstance().getWorld();
        }
        this.location = new Location(world, x, y, Direction.RIGHT);
    }

    public void setLocation(Vec2 location) {
        setLocation(location.getX(), location.getY());
    }

    public boolean canTarget(Entity entity) {
        EntityType type = entity.getEntityType();
        return type.isHumanNPC() && type != EntityType.OLD_MAN;
    }

    public Entity getClosestTargetable() {
        Entity target = null;
        double closestDistance = 0;
        for(Entity entity : getLocation().getWorld().getEntities()) {
            if(canTarget(entity)) {
                double distance = entity.getLocation().distanceSquared(getLocation());
                if(target == null || distance < closestDistance) {
                    target = entity;
                    closestDistance = distance;
                }
            }
        }
        return target;
    }

    public Vec2 getVelocity() {
        return velocity;
    }

    public void setVelocity(double x, double y) {
        this.velocity = new Vec2(x, y);
    }

    public int getDirectionAsInt() {
        return location.getDirectionX() == Direction.RIGHT ? 1 : -1;
    }

    public void flagForUpdate() {
        setNeedsNetUpdate(true);
    }


}
