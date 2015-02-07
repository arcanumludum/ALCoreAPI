package com.arcanumLudum.ALCore.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import com.arcanumLudum.ALCore.math.MathHelper;
import com.arcanumLudum.ALCore.math.vec.Vector;
import com.arcanumLudum.ALCore.math.vec.Vector3;
import com.arcanumLudum.ALCore.world.WorldHelper;

public class ShapeHelper 
{
	public static int makeSphere(Vector3 pos, Block block, double radiusX, double radiusY, double radiusZ, boolean filled) {
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

                    if (WorldHelper.setBlock(pos.add(x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, -y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, -y, -z), block)) {
                        ++affected;
                    }
                }
            }
        }

        return affected;
    }
	
	public static int replaceSphere(Vector3 pos, Block block, double radiusX, double radiusY, double radiusZ, boolean filled) {
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

                    if (WorldHelper.replaceBlock(pos.add(x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, -y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, -y, -z), block)) {
                        ++affected;
                    }
                }
            }
        }

        return affected;
    }
	
	public static int makeSphereCentered(Vector3 pos, Block block, double radiusX, double radiusY, double radiusZ, boolean filled) {
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
        forX: for (int x = 0; x <= ceilRadiusX + 1; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY - 1; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ + 1; ++z) {
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

                    if (WorldHelper.setBlock(pos.add(x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(x, -y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.setBlock(pos.add(-x, -y, -z), block)) {
                        ++affected;
                    }
                }
            }
        }

        return affected;
    }
	
	public static int replaceSphereCentered(Vector3 pos, Block block, double radiusX, double radiusY, double radiusZ, boolean filled) {
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
        forX: for (int x = 0; x <= ceilRadiusX + 1; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY - 1; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ + 1; ++z) {
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

                    if (WorldHelper.replaceBlock(pos.add(x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, -y, z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(x, -y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, y, -z), block)) {
                        ++affected;
                    }
                    if (WorldHelper.replaceBlock(pos.add(-x, -y, -z), block)) {
                        ++affected;
                    }
                }
            }
        }

        return affected;
    }
	
	public static int replaceBiomeSphereCentered(Vector3 pos, BiomeGenBase biome, double radiusX, double radiusY, double radiusZ, boolean filled, boolean snow) 
	{		
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

		if(snow)
		{
			biome = biome.createMutation();
		}
        
        double nextXn = 0;
        forX: for (int x = 0; x <= ceilRadiusX + 1; ++x) {
            final double xn = nextXn;
            nextXn = (x + 1) * invRadiusX;
            double nextYn = 0;
            forY: for (int y = 0; y <= ceilRadiusY - 1; ++y) {
                final double yn = nextYn;
                nextYn = (y + 1) * invRadiusY;
                double nextZn = 0;
                forZ: for (int z = 0; z <= ceilRadiusZ + 1; ++z) {
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
                    
                    if(y < (radiusY / 2))
                    {
                    	if (WorldHelper.replaceBlock(pos.add(x, y, z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, y, z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, -y, z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, y, -z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, -y, z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, -y, -z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, y, -z), biome.topBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, -y, -z), biome.topBlock)) {
                            ++affected;
                        }
                    }
                    else
                    {
                    	if (WorldHelper.replaceBlock(pos.add(x, y, z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, y, z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, -y, z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, y, -z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, -y, z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(x, -y, -z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, y, -z), biome.fillerBlock)) {
                            ++affected;
                        }
                        if (WorldHelper.replaceBlock(pos.add(-x, -y, -z), biome.fillerBlock)) {
                            ++affected;
                        }
                    }
                }
            }
        }

        return affected;
    }
	
	public int makePyramid(Vector3 position, Block block, int size, boolean filled) {
        int affected = 0;

        int height = size;

        for (int y = 0; y <= height; ++y) {
            size--;
            for (int x = 0; x <= size; ++x) {
                for (int z = 0; z <= size; ++z) {

                    if ((filled && z <= size && x <= size) || z == size || x == size) {

                        if (WorldHelper.setBlock(position.add(x, y, z), block)) {
                            ++affected;
                        }
                        if (WorldHelper.setBlock(position.add(-x, y, z), block)) {
                            ++affected;
                        }
                        if (WorldHelper.setBlock(position.add(x, y, -z), block)) {
                            ++affected;
                        }
                        if (WorldHelper.setBlock(position.add(-x, y, -z), block)) {
                            ++affected;
                        }
                    }
                }
            }
        }

        return affected;
    }
	
	public static int makeCube(Vector3 position, Block block, int r)
	{
		int affected = 0;
		
		for(int x = (int) (position.x - r); x < position.x + r; x++)
        {
        	for(int y = (int) (position.y - r) - r; y < position.y + r; y++)
	        {
        		for(int z = (int) (position.z - r) - r; z < position.z + r; z++)
    	        {
        			WorldHelper.setBlock(new Vector3(x, y, z), block);
        			
    				affected++;
    	        }
	        }
        }
		
		return affected;
	}
	
	public static int replaceCube(Vector3 position, Block block, int r)
	{
		int affected = 0;
		
		for(int x = (int) (position.x - r); x < position.x + r; x++)
        {
        	for(int y = (int) (position.y - r) - r; y < position.y + r; y++)
	        {
        		for(int z = (int) (position.z - r) - r; z < position.z + r; z++)
    	        {
        			WorldHelper.replaceBlock(new Vector3(x, y, z), block);
        			
    				affected++;
    	        }
	        }
        }
		
		return affected;
	}
	
	public static int makeCubeCentered(Vector3 position, Block block, int r)
	{
		int affected = 0;
		
		for(int x = (int) (position.x - r); x < position.x + (r + 1); x++)
        {
        	for(int y = (int) (position.y - r) - r; y < position.y + (r + 1); y++)
	        {
        		for(int z = (int) (position.z - r) - r; z < position.z + (r + 1); z++)
    	        {
        			WorldHelper.setBlock(new Vector3(x, y, z), block);
        			
    				affected++;
    	        }
	        }
        }
		
		return affected;
	}
	
	public static int replaceCubeCentered(Vector3 position, Block block, int r)
	{
		int affected = 0;
		
		for(int x = (int) (position.x - r); x < position.x + (r + 1); x++)
        {
        	for(int y = (int) (position.y - r) - r; y < position.y + (r + 1); y++)
	        {
        		for(int z = (int) (position.z - r) - r; z < position.z + (r + 1); z++)
    	        {
        			WorldHelper.replaceBlock(new Vector3(x, y, z), block);
        			
    				affected++;
    	        }
	        }
        }
		
		return affected;
	}
}
