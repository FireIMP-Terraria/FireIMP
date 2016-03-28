package net.fireimp.server.world.generator.passes;

import net.fireimp.server.util.Vec2;
import net.fireimp.server.datatypes.enums.Liquid;
import net.fireimp.server.util.Maths;
import net.fireimp.server.world.World;

public class TunnelPass extends GenerationPass {
    public TunnelPass(World world) {
        super(world);
    }

    @Override
    public void run() {
        for (int x = 0; x < world.getSize().getWidth() * 0.0015; ++x) {
            int[] xVals = new int[10];
            int[] yVals = new int[10];
            int seedX = nextInt(450, world.getSize().getWidth() - 450);
            int seedY = 0;
            for (int i = 0; i < 10; ++i) {
                while (!world.getTileAt(seedX, seedY).isActive()) {
                    seedY++;
                }
                xVals[i] = seedX;
                yVals[i] = seedY;
                seedX += nextInt(5, 11);
            }
            for (int i = 0; i < 10; ++i) {
                stepCaves(xVals[i], yVals[i], nextInt(5, 8), nextInt(6, 9), 0, true, -2f, -0.3f, false, true);
                stepCaves(xVals[i], yVals[i], nextInt(5, 8), nextInt(6, 9), 0, true, 2f, -0.3f, false, true);
            }
        }
    }

    private void stepCaves(int x, int y, double strength, float steps, int type, boolean addTiles, float speedX, float speedY, boolean noYChange, boolean override) {
        Vec2 startLocation = new Vec2(x, y);
        Vec2 speedVec;
        if (speedX != 0f || speedY != 0f) {
            speedVec = new Vec2(speedX, speedY);
        } else {
            speedVec = new Vec2(nextInt(-10, 11) * 0.1f, nextInt(-10, 11) * 0.1f);
        }
        //TODO: Rename types
        boolean flag1 = type == 368;
        boolean flag2 = type == 368;
        while (strength > 0.0 && (double) steps > 0.0) {
            if (startLocation.getY() < 0 && steps > 0 && type == 59) {
                steps = 0f;
            }
            strength = strength * (double) (steps / steps);
            steps--;
            int xMin = (int) (startLocation.getX() - strength * 0.5);
            int xMax = (int) (startLocation.getX() + strength * 0.5);
            int yMin = (int) (startLocation.getY() - strength * 0.5);
            int yMax = (int) (startLocation.getY() + strength * 0.5);
            xMin = Maths.clamp(xMin, 1, world.getSize().getWidth() - 1);
            xMax = Maths.clamp(xMax, 1, world.getSize().getWidth() - 1);
            yMin = Maths.clamp(yMin, 1, world.getSize().getHeight() - 1);
            yMax = Maths.clamp(yMax, 1, world.getSize().getHeight() - 1);
            for (int xVal = xMin; xVal < xMax; ++xVal) {
                for (int yVal = yMin; yVal < yMax; ++yVal) {
                    double xDist = Math.abs(xVal - startLocation.getX());
                    double yDist = Math.abs(yVal - startLocation.getY());
                    //TODO: Add support for wall types
//                    if (WorldGen.mudWall && (double) j1 > Main.worldSurface && ((int) Main.tile[i1, j1 - 1].wall != 2 && j1 < Main.maxTilesY - 210 - WorldGen.genRand.Next(3)))
//                    {
//                        if (j1 > WorldGen.lavaLine - WorldGen.genRand.Next(0, 4) - 50)
//                        {
//                            if ((int) Main.tile[i1, j1 - 1].wall != 64 && (int) Main.tile[i1, j1 + 1].wall != 64 && ((int) Main.tile[i1 - 1, j1].wall != 64 && (int) Main.tile[i1, j1 + 1].wall != 64))
//                            WorldGen.PlaceWall(i1, j1, 15, true);
//                        }
//                        else if ((int) Main.tile[i1, j1 - 1].wall != 15 && (int) Main.tile[i1, j1 + 1].wall != 15 && ((int) Main.tile[i1 - 1, j1].wall != 15 && (int) Main.tile[i1, j1 + 1].wall != 15))
//                        WorldGen.PlaceWall(i1, j1, 64, true);
//                    }
                    if (xDist + yDist < strength * 0.5 * (1 + nextDouble(-10, 11) * 0.015)) {
                        if (type < 0) {
                            if (type == -2 && world.getTileAt(xVal, yVal).isActive() && (y < world.getWaterLayer() || y > world.getLavaLayer())) {
                                world.getTileAt(xVal, yVal).setLiquidType(Liquid.WATER);
                            }
                        } else {
                            //TODO: More wall stuff
//                            if (flag1 && (double) Math.Abs((float) i1 - startLocation.X) + (double) Math.Abs((float) j1 - startLocation.Y) < strength * 0.3 * (1.0 + (double) WorldGen.genRand.Next(-10, 11) * 0.01))
//                                WorldGen.PlaceWall(i1, j1, 180, true);
//                            if (flag2 && (double) Math.Abs((float) i1 - startLocation.X) + (double) Math.Abs((float) j1 - startLocation.Y) < strength * 0.3 * (1.0 + (double) WorldGen.genRand.Next(-10, 11) * 0.01))
//                                WorldGen.PlaceWall(i1, j1, 178, true);

                            //Can we set this tile?
                            if (override || !world.getTileAt(xVal, yVal).isActive()) {
                                boolean exemptFromChange = false;
//                                Tile tile = Main.tile[i1, j1];
//                                bool flag3 = Main.tileStone[type] && (int) tile.type != 1;
//                                if (!TileID.Sets.CanBeClearedDuringGeneration[(int) tile.type])
//                                    flag3 = true;
                                int id = world.getTileAt(xVal, yVal).getTypeId();
                                if (id <= 147) {
                                    if (id <= 45) {
                                        if (id != 1) {
                                            if (id != 45) {
//                                            goto label_54;
                                            }
                                        } else if (type == 59 && yVal < world.getWorldInfo().getSurfaceLayer() + nextInt(-50, 50)) {
                                            exemptFromChange = true;
//                                                goto label_54;
                                        } else {
//                                                goto label_54;
                                        }
                                    } else if (id != 53) {
                                        if(id != 147) {
//                                            goto label_54;
                                        }
                                    } else {
                                        if (type == 40 || (yVal <= world.getWorldInfo().getSurfaceLayer() && type != 59)) {
                                            exemptFromChange = true;
                                        }
                                        //goto label_54;
                                    }
                                } else if(id <= 196) {
                                    switch(id) {
                                        case 189:
                                        case 190:
                                        case 196:
                                        break;
                                        default:
                                            //goto label_54;
                                    }
                                } else {
                                    switch (id) {
                                        case 367:
                                        case 368:
                                            if(type == 59) {
                                                exemptFromChange = true;
                                            }
                                            break;
                                        case 396:
                                        case 397:
//                                            flag3 = !TileID.Sets.Ore[type];
//                                            goto label_54;
                                            break;
                                        default:
                                            //goto label_54;
                                            //break;
                                    }
                                }
                                //flags3 = true
                                if (!exemptFromChange) {
                                    world.getTileAt(xVal, yVal).setTileId(1);
                                }
                            }
//                            if(addTiles) {
//                                Main.tile[i1, j1].active(true);
//                                Main.tile[i1, j1].liquid = (byte) 0;
//                                Main.tile[i1, j1].lava(false);
//                            }
//                            if (noYChange && (double) j1 < Main.worldSurface && type != 59)
//                                Main.tile[i1, j1].wall = (byte) 2;
//                            if (type == 59 && j1 > WorldGen.waterLine && (int) Main.tile[i1, j1].liquid > 0)
//                            {
//                                Main.tile[i1, j1].lava(false);
//                                Main.tile[i1, j1].liquid = (byte) 0;
//                            }
                        }
                    }
                }
            }
            startLocation.setX(startLocation.getX() + speedVec.getX());
            startLocation.setY(startLocation.getY() + speedVec.getY());
            for (int i = 50; i < 900; i += 50) {
                if(strength > i) {
                    startLocation.setX(startLocation.getX() + speedVec.getX());
                    startLocation.setY(startLocation.getY() + speedVec.getY());
                    steps--;
                    speedVec.setX(speedVec.getX() + (nextInt(-10, 11) * 0.05f));
                    speedVec.setY(speedVec.getY() + (nextInt(-10, 11) * 0.05f));
                } else {
                    break;
                }
            }
            speedVec.setX(speedVec.getX() + (nextInt(-10, 11) * 0.05f));
            speedVec.setX(Maths.clamp(speedVec.getX(), -1, 1));
            if(!noYChange) {
                speedVec.setY(speedVec.getY() + (nextInt(-10, 11) * 0.05f));
                speedVec.setY(Maths.clamp(speedVec.getY(), -1, 1));
            } else if(type != 59 && strength < 3) {
                speedVec.setY(Maths.clamp(speedVec.getY(), -1, 1));
            }
            if(type == 59 && !noYChange) {
                speedVec.setX(Maths.clamp(speedVec.getX(), -0.5, 0.5));
                if(startLocation.getY() < world.getWorldInfo().getRockLayer() + 100) {
                    speedVec.setY(1f);
                }
                if(startLocation.getY() >  world.getSize().getHeight() - 300) {
                    speedVec.setY(-1f);
                }
            }
        }
    }

}
