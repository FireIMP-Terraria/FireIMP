package net.fireimp.server.entities;

import net.fireimp.server.datatypes.enums.EntityType;
import net.fireimp.server.entities.ai.AIStyleSlime;

public class EntitySlime extends Entity {

    public EntitySlime(EntityType slimeType) {
        super(slimeType);
        setMaxHealth(25);
        setHealth(getMaxHealth());
        setAiStyle(new AIStyleSlime());
    }
}
