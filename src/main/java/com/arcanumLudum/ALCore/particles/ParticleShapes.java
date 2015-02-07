package com.arcanumLudum.ALCore.particles;

import com.arcanumLudum.ALCore.colour.ColourRGB;
import com.arcanumLudum.ALCore.math.MathHelper;
import com.arcanumLudum.ALCore.math.vec.Vector3;
import com.arcanumLudum.ALCore.world.WorldHelper;

public class ParticleShapes {
	public static int makeSphere(Vector3 pos, ColourRGB colour, String particleName, double radiusX, double radiusY, double radiusZ, boolean filled, float expansionRate) {
        int affected = 0;

        radiusX += 0.5;
        radiusY += 0.5;
        radiusZ += 0.5;

        final double invRadiusX = 1 / radiusX;
        final double invRadiusY = 1 / radiusY;
        final double invRadiusZ = 1 / radiusZ;

        final int ceilRadiusX = (int) Math.ceil(radiusX);
        final int ceilRadiusY = (int) Math.ceil(radiusY);
        final int ceilRadiusZ = (int) Math.ceil(radiusZ);

        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ; ++z) {
                    final double zn = nextZn;
                    nextZn = (z + 1) * invRadiusZ;

                    double distanceSq = MathHelper.lengthSq(xn, yn, zn);
                    if (distanceSq > 1) {
                        if (z == 0) {
                            if (y == 0) {
                                break forX;
                            }
                            break forY;
                        }
                        break forZ;
                    }

                    if (!filled) {
                        if (MathHelper.lengthSq(nextXn, yn, zn) <= 1 && MathHelper.lengthSq(xn, nextYn, zn) <= 1 && MathHelper.lengthSq(xn, yn, nextZn) <= 1) {
                            continue;
                        }
                    }
                    
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(x, y, z), expansionRate, expansionRate, expansionRate)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(-x, y, z), 0, expansionRate, expansionRate)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(x, -y, z), expansionRate, 0, expansionRate)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(x, y, -z), expansionRate, expansionRate, 0)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(-x, -y, z), 0, 0, expansionRate)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(x, -y, -z), expansionRate, 0, 0)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(-x, y, -z), 0, expansionRate, 0)) {
                        ++affected;
                    }
                    if (WorldHelper.spawnParticle(particleName, colour, pos.add(-x, -y, -z), 0, 0, 0)) {
                        ++affected;
                    }
                }
            }
        }

        return affected;
    }
}
