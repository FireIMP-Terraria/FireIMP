package net.fireimp.server.world.generator.passes;

import net.fireimp.server.world.World;

import java.util.Random;

public abstract class GenerationPass {
    protected final Random random;
    protected final World world;

    public GenerationPass(World world) {
        this.world = world;
        this.random = world.getRandom();
    }

    public abstract void run();

    protected double nextDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    protected int nextInt(int min, int max) {
        return min + random.nextInt(max - min);
    }
}
