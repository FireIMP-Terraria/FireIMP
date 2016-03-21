package net.fireimp.server.world.generator;

import net.fireimp.server.world.World;
import net.fireimp.server.world.generator.passes.TerrainPass;
import net.fireimp.server.world.generator.passes.TunnelPass;

public class WorldGenerator {
    private final World world;
    private final TerrainPass pass;

    public WorldGenerator(World world) {
        this.world = world;
        this.pass = new TerrainPass(world);
    }

    public void generate() {
        System.out.println("Generating world");
        pass.run();
        new TunnelPass(world).run();
        System.out.println("Complete!");
    }
}