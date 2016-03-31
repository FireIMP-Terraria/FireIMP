package net.fireimp.server.entities.ai;

import net.fireimp.server.TerrariaServer;
import net.fireimp.server.datatypes.enums.EntityType;
import net.fireimp.server.entities.Direction;
import net.fireimp.server.entities.Entity;
import net.fireimp.server.util.Maths;
import net.fireimp.server.util.Vec2;

public class AIStyleSlime implements AIStyle {
    @Override
    public int getStyleId() {
        return 1;
    }

    @Override
    public void applyToEntity(Entity entity) {
        float[] aiFloats = entity.getAiFloats();
        //I have no idea what pretty much everything below here means.
        if(entity.getEntityType() == EntityType.BLUE_SLIME && (aiFloats[1] == 1f || aiFloats[1] == 2f || aiFloats[1] == 3f)) {
            aiFloats[1] = -1f;
        }
        if(entity.getEntityType() == EntityType.BLUE_SLIME && aiFloats[1] == 0f && entity.getDropValue() > 0f) {
            aiFloats[1] = -1;
            if(TerrariaServer.GLOBAL_RANDOM.nextInt(20) == 0) {
                int num;
                switch (TerrariaServer.GLOBAL_RANDOM.nextInt(4)) {
                    case 0:
                        switch (TerrariaServer.GLOBAL_RANDOM.nextInt(7)) {
                            case 0:
                                num = 290;
                                break;
                            case 1:
                                num = 292;
                                break;
                            case 3:
                                num = 2322;
                                break;
                            default:
                                num = 2350; //2997 if !netmode == 0 && TerrariaServer.GLOBAL_RANDOM == 0.
                                break;
                        }
                        break;
                    case 1:
                        switch (TerrariaServer.GLOBAL_RANDOM.nextInt(4)) {
                            case 0:
                                num = 8;
                                break;
                            case 1:
                                num = 166;
                                break;
                            case 2:
                                num = 965;
                                break;
                            default:
                                num = 58;
                                break;
                        }
                        break;
                    case 2:
                        num = TerrariaServer.GLOBAL_RANDOM.nextInt(2) != 0 ? Maths.nextInt(699, 703) : Maths.nextInt(11, 15);
                        break;
                    default:
                        switch (TerrariaServer.GLOBAL_RANDOM.nextInt(3)) {
                            case 0:
                                num = 71;
                                break;
                            case 1:
                                num = 72;
                                break;
                            default:
                                num = 73;
                                break;
                        }
                }
                aiFloats[1] = num;
                entity.flagForUpdate();
                if (aiFloats[2] > 1) {
                    aiFloats[2]--;
                }
              /*  if (this.wet)
                {
                    if (this.collideY)
                        this.velocity.Y = -2f;
                    if ((double) this.velocity.Y < 0.0 && (double) this.ai[3] == (double) this.position.X)
                    {
                        this.direction *= -1;
                        this.ai[2] = 200f;
                    }
                    if ((double) this.velocity.Y > 0.0)
                        this.ai[3] = this.position.X;
                    if (this.type == 59)
                    {
                        if ((double) this.velocity.Y > 2.0)
                            this.velocity.Y *= 0.9f;
                        else if (this.directionY < 0)
                            this.velocity.Y -= 0.8f;
                        this.velocity.Y -= 0.5f;
                        if ((double) this.velocity.Y < -10.0)
                            this.velocity.Y = -10f;
                    }
                    else
                    {
                        if ((double) this.velocity.Y > 2.0)
                            this.velocity.Y *= 0.9f;
                        this.velocity.Y -= 0.5f;
                        if ((double) this.velocity.Y < -4.0)
                            this.velocity.Y = -4f;
                    }
                    if ((double) this.ai[2] == 1.0 && flag)
                        this.TargetClosest(true);
                }*/
            }
            if(aiFloats[2] == 0f) {
                aiFloats[0] = -100f;
                aiFloats[2] = 1f;
                entity.setTargetId((byte) entity.getClosestTargetable().getId());
            }
            if(entity.getVelocity().getY() == 0) {
                if(aiFloats[3] == entity.getLocation().getX()) {
                    entity.getLocation().flipDirection();
                    aiFloats[2] = 200f;
                }
                aiFloats[3] = 0f;
                Vec2 velocity = entity.getVelocity();
                velocity.setX(velocity.getX() * 0.8f);
                if(velocity.getX() > -0.1 && velocity.getX() < 0.1f) {
                    velocity.setX(0f);
                }
                entity.setVelocity(velocity);
                /*if(flag) {
                    aiFloats[0]++;
                }*/
                aiFloats[0]++;
                if(entity.getEntityType() == EntityType.LAVA_SLIME || entity.getEntityType() == EntityType.ILLUMINANT_SLIME) {
                    aiFloats[0] += 2f;
                } else if(entity.getEntityType() == EntityType.DUNGEON_SLIME) {
                    aiFloats[0] += 3f;
                } else if(entity.getEntityType() == EntityType.CRIMSLIME) {
                    aiFloats[0]++;
                } else if(entity.getEntityType() == EntityType.HOPPIN_JACK) {
                    if(entity.getScale() >= 0f) {
                        aiFloats[0] += 4f;
                    } else {
                        aiFloats[0]++;
                    }
                }
                int num = 0;
                if(aiFloats[0] >= 0f) {
                    num = 1;
                }
                if(aiFloats[0] >= -1000 && aiFloats[0] <= -500) {
                    num = 2;
                }
                if(aiFloats[0] >= -2000 && aiFloats[0] <= -1500) {
                    num = 3;
                }
                if(num > 0) {
                    entity.flagForUpdate();
//                    if (flag && (double) this.ai[2] == 1.0)
//                        this.TargetClosest(true);
                    velocity = entity.getVelocity();
                    if(num == 3) {
                        velocity.setY(-8f);
                        if(entity.getEntityType() == EntityType.LAVA_SLIME) {
                            velocity.setY(velocity.getY() - 2f);
                        }
                        velocity.setX(velocity.getX() + (3 * entity.getDirectionAsInt()));
                        if(entity.getEntityType() == EntityType.LAVA_SLIME) {
                            velocity.setX(velocity.getX() + (0.5 * entity.getDirectionAsInt()));
                        }
                        aiFloats[0] = -200f;
                        aiFloats[3] = (float) entity.getLocation().getX();
                    } else {
                        velocity.setY(-6f);
                        velocity.setX(velocity.getX() + (2 * entity.getDirectionAsInt()));
                        if(entity.getEntityType() == EntityType.LAVA_SLIME) {
                            velocity.setX(velocity.getX() + (2 * entity.getDirectionAsInt()));
                        }
                        aiFloats[0] = -120f;
                        if(num == 1)
                            aiFloats[0] -= 1000f;
                        else
                            aiFloats[0] -= 2000f;
                    }
                    if(entity.getEntityType() == EntityType.TOXIC_SLUDGE) {
                        velocity.setX(velocity.getX() * 1.2f);
                        velocity.setY(velocity.getY() * 1.3f);
                    }
                    entity.setVelocity(velocity);
                    //Apparently grasshoppers use slime AI
                    if(entity.getEntityType() != EntityType.GRASSHOPPER && entity.getEntityType() != EntityType.GOLD_GRASSHOPPER) {
                        entity.setAiFloats(aiFloats);
                        return;
                    }
                    velocity = entity.getVelocity();
                    velocity.setX(velocity.getX() * 0.6f);
                    velocity.setY(velocity.getY() * 0.9f);
                    entity.setAiFloats(aiFloats);
                    entity.setVelocity(velocity);
                    /*
                      if(!flag)
                        return;
                      this.direction = -this.direction;
                      this.velocity.X *= -1f;
                     */
                } /*else {
                    if(aiFloats[0] < -30.0)
                        return;
                    this.aiAction = 1;
                }*/
            } else {
                Vec2 velocity = entity.getVelocity();
                if(entity.getTargetId() >= Byte.MAX_VALUE || (entity.getDirectionAsInt() != 1 && velocity.getX() >= 3) && (entity.getDirectionAsInt() != -1 || velocity.getX() <= 3)) {
                    return;
                }
                if(entity.getDirectionAsInt() == -1 && velocity.getX() < 0.1 || entity.getDirectionAsInt() == 1 && velocity.getX() > -0.1) {
                    velocity.setX(velocity.getX() + (0.2f * entity.getDirectionAsInt()));
                } else  {
                    velocity.setX(velocity.getX() * 0.93f);
                }
                entity.setVelocity(velocity);
            }
        }
    }
}
