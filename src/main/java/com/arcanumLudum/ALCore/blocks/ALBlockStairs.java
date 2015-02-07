package com.arcanumLudum.ALCore.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class ALBlockStairs extends BlockStairs
{
	protected ALBlockStairs(Block block, int meta) 
	{
		super(block, meta);
		
		useNeighborBrightness = true;
	}
}