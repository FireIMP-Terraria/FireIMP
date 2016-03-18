package net.fireimp.server.test;

import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;
import org.junit.Assert;
import org.junit.Test;

public class WorldGenTest {

    @Test
    public void testWorldGen() {
        // TODO: Make this useful.
        // stuff like: check if there are enough trees, dungeon, crimson/corruption, chests ect.
        World world = new World(WorldSize.MEDIUM);
        world.generate(); // Generate world in RAM
        Assert.assertTrue("Top block isn't air!", world.getTileAt(0, 0).getTypeId() == -1);
    }
}
