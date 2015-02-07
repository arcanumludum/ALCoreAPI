package com.arcanumLudum.ALCore.blocks;

import com.arcanumLudum.ALCore.colour.Colour;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

public class ALColourBlock extends Block {
	private int meta;

	protected ALColourBlock(Material p_i45394_1_, int meta) {
		super(p_i45394_1_);

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
