package com.arcanumLudum.ALCore.blocks;

import com.arcanumLudum.ALCore.colour.Colour;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.world.IBlockAccess;

public class ALColourBlockStairs extends BlockStairs {
	private int meta;

	protected ALColourBlockStairs(Block block, int meta) {
		super(block, meta);

		useNeighborBrightness = true;
		this.meta = meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int p_149741_1_) {
		return Colour.colourCode[this.meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_,
			int p_149720_3_, int p_149720_4_) {
		return Colour.colourCode[this.meta];
	}
}