package net.fireimp.server.entities.ai;

import net.fireimp.server.entities.Entity;

public interface AIStyle {
    int getStyleId();
    void applyToEntity(Entity entity);
}
