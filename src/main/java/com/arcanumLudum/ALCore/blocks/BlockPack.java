package com.arcanumLudum.ALCore.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockPack 
{
	public static ALBlock[] blockList = new ALBlock[100];
	public static ALBlockStairs[] stairsList = new ALBlockStairs[100];
	public static ALBlockLadder[] laddersList = new ALBlockLadder[100];
	
	public static void addPackBig(int packID, String textureName, String packName, Material material, CreativeTabs tab)
	{
		if(blockList[packID] == null)
		{
			blockList[packID] = (ALBlock) new ALBlock(material).setBlockName(packName).setCreativeTab(tab).setBlockTextureName(textureName);
		}

		if(stairsList[packID] == null)
		{
			stairsList[packID] = (ALBlockStairs) new ALBlockStairs(blockList[packID], 0).setBlockName(packName).setCreativeTab(tab);
		}
		
		if(laddersList[packID] == null)
		{
			laddersList[packID] = (ALBlockLadder) new ALBlockLadder().setBlockName(packName).setCreativeTab(tab).setBlockTextureName(textureName);
		}
	}
	
	public static void addPackMedium(int packID, String textureName, String packName, Material material, CreativeTabs tab)
	{
		if(blockList[packID] == null)
		{
			blockList[packID] = (ALBlock) new ALBlock(material).setBlockName(packName).setCreativeTab(tab).setBlockTextureName(textureName);
		}

		if(stairsList[packID] == null)
		{
			stairsList[packID] = (ALBlockStairs) new ALBlockStairs(blockList[packID], 0).setBlockName(packName).setCreativeTab(tab);
		}
	}
	
	public static void registerPackBig(int packID, String packName)
	{
		GameRegistry.registerBlock(blockList[packID], "block_" + packName);
		GameRegistry.registerBlock(stairsList[packID], "stair_" + packName);
		GameRegistry.registerBlock(laddersList[packID], "ladder_" + packName);
	}
	
	public static void registerPackMedium(int packID, String packName)
	{
		GameRegistry.registerBlock(blockList[packID], "block_" + packName);
		GameRegistry.registerBlock(stairsList[packID], "stair_" + packName);
	}
}
