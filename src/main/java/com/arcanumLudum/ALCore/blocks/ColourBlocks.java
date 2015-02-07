package com.arcanumLudum.ALCore.blocks;

import net.minecraft.block.Block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ColourBlocks
{
	public ALColourBlock[] blockArr = new ALColourBlock[16];
	
	public void registerBlocks()
	{
		for (int i = 0; i < 16; i++) {
			GameRegistry.registerBlock(blockArr[i], blockArr[i].getUnlocalizedName());
		}
	}
	
	public void loadBlocks(String textureBaseName, String blockBaseName, Material material, CreativeTabs tab, SoundType sound, float hardness, float slipperiness, float lightLevel, int lightOpacity)
	{
		for(int i = 0; i < 16; i++)
		{
			blockArr[i] = (ALColourBlock) new ALColourBlock(material, i).setBlockName(blockBaseName + "_" + i).setBlockTextureName(textureBaseName).setCreativeTab(tab).setHardness(hardness).setLightLevel(lightLevel).setLightOpacity(lightOpacity).setStepSound(sound);
			blockArr[i].slipperiness = slipperiness;
		}
	}
}
