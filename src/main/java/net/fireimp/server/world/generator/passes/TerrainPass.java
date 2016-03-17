package net.fireimp.server.world.generator.passes;

import net.fireimp.server.util.Maths;
import net.fireimp.server.world.World;
import net.fireimp.server.world.WorldSize;

public class TerrainPass extends GenerationPass {
    private int modificationType;
    private int modificationSize;

    public TerrainPass(World world) {
        super(world);
    }

    @Override
    public void run() {
        final WorldSize size = world.getSize();
        modificationType = 0; // the type of current modification
        modificationSize = 0; // the size of current modification

        // Generate surface level
        double worldSurface = size.getHeight() * 0.3; // 30% is surface
        worldSurface *= nextInt(90, 110) * 0.01; // +/- 10%

        // Generate rock level
        double rockLayer = worldSurface + size.getHeight() * 0.2; // 20% is rock
        rockLayer *= nextInt(90, 110) * 0.01; // +/- 10%

        double worldSurfaceLow = worldSurface;
        double worldSurfaceHigh = worldSurface;
        double rockLayerLow = rockLayer;
        double rockLayerHigh = rockLayer;

        // Loop through each column of blocks
        for(int x = 0; x < size.getTileCount(); x++) {
            float progress = x / (float) size.getWidth(); // TODO: Show progress in log

            // adjust surface level bounds
            if(worldSurface < worldSurfaceLow) {
                worldSurfaceLow = worldSurface;
            } if(worldSurface > worldSurfaceHigh) {
                worldSurfaceHigh = worldSurface;
            } if(rockLayer < rockLayerLow) {
                rockLayerLow = rockLayer;
            } if(rockLayer > rockLayerHigh) {
                rockLayerHigh = rockLayer;
            }

            // calculate modification levels
            if(modificationSize <= 0) {
                modificationType = nextInt(0, 5);
                modificationSize = nextInt(5, 40);
                if(modificationType == 0) {
                    modificationSize *= (int) (nextInt(5, 30) * 0.2);
                }
            }
            --modificationSize;

            // Have more modest plain modifications near world center (where player spawns)
            if(x > size.getWidth() * 0.43 && x < size.getWidth() * 0.57 && modificationType >= 3) {
                modificationType = random.nextInt(3);
            } if(x > size.getWidth() * 0.47 && x < size.getWidth() * 0.53) {
                modificationType = 0;
            }

            // transform surface and rock level
            worldSurface = transformSurfaceLevel(worldSurface, modificationType);
            worldSurface = limitSurfaceLevel(worldSurface, x, size);
            rockLayer = tranformRockLevel(rockLayer, worldSurface, size);

            // Set blocks. Note: vanilla set all blocks above surface level to inactive
            for(int y = (int) worldSurface; y < size.getHeight(); y++) {
                if(y < rockLayer) {
                    // set to dirt
                    world.getTileAt(x, y).setTileId(0);
                } else {
                    // set to stone
                    world.getTileAt(x, y).setTileId(1);
                }
            }
        }

        // TODO: Set world info
    }

    private double tranformRockLevel(double rockLayer, double worldSurface, WorldSize size) {
        while(random.nextInt(3) == 0) {
            // rough height patches in rock layer
            rockLayer += nextInt(-2, 3);
        }
        // Try to keep between 5-35% after surface level
        if(rockLayer < worldSurface + size.getHeight() * 0.05) {
            ++rockLayer;
        }
        if(rockLayer > worldSurface + size.getHeight() * 0.35) {
            --rockLayer;
        }
        return rockLayer;
    }

    private double limitSurfaceLevel(double worldSurface, int index, WorldSize size) {
        // Set overall limit to 30%
        worldSurface = Maths.clamp(worldSurface, size.getHeight() * 0.17, size.getHeight() * 0.3);

        // Set near-edge limit to 25%
        if ((index < 275 || index > size.getWidth() - 275) && worldSurface > size.getHeight() * 0.25) {
            worldSurface = size.getHeight() * 0.25;
            modificationSize = 1;
        }
        return worldSurface;
    }

    private double transformSurfaceLevel(double worldSurface, int intensity) {
        // Apply surface level modifications
        switch(intensity) {
            case 0:
                // Increase or decrease height by 1 (small height change)
                while(random.nextInt(7) == 0) {
                    worldSurface += nextInt(-1, 2);
                }
                break;
            case 1:
                // downward hills
                while(random.nextInt(4) == 0) {
                    --worldSurface;
                }
                while(random.nextInt(10) == 0) {
                    ++worldSurface;
                }
                break;
            case 2:
                // upward hills
                while(random.nextInt(4) == 0) {
                    ++worldSurface;
                }
                while(random.nextInt(10) == 0) {
                    --worldSurface;
                }
                break;
            case 3:
                // big downward hills
                while(random.nextInt(2) == 0) {
                    --worldSurface;
                }
                while(random.nextInt(6) == 0) {
                    ++worldSurface;
                }
                break;
            case 4:
                // big upward hills
                while(random.nextInt(2) == 0) {
                    ++worldSurface;
                }
                while(random.nextInt(6) == 0) {
                    --worldSurface;
                }
                break;
            default:
                // no modifications.
                break;
        }
        return worldSurface;
    }
}
