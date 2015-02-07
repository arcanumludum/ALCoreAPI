package com.arcanumLudum.ALCore.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.registry.GameRegistry;

public class ColourBlockPack {
	public static ALColourBlock[] blockList = new ALColourBlock[96];
	public static ALColourBlockStairs[] stairsList = new ALColourBlockStairs[96];
	public static ALColourBlockLadder[] laddersList = new ALColourBlockLadder[96];

	public static void addPackBig(String textureName, String packName,
			Material material, CreativeTabs tab) {
		for (int i = 0; i < 16; i++) {
			if (blockList[i] == null) {
				blockList[i] = (ALColourBlock) new ALColourBlock(material, i)
						.setBlockName("block_" + packName + "_" + i)
						.setCreativeTab(tab).setBlockTextureName(textureName);
			}

			if (stairsList[i] == null) {
				stairsList[i] = (ALColourBlockStairs) new ALColourBlockStairs(
						blockList[i], i).setBlockName(
						"stairs_" + packName + "_" + i).setCreativeTab(tab);
			}
		}
		
		if (laddersList[0] == null) {
			laddersList[0] = (ALColourBlockLadder) new ALColourBlockLadder(
					0).setBlockName("ladder_" + packName)
					.setCreativeTab(tab).setBlockTextureName(textureName);
		}
	}

	public static void addPackMedium(String textureName, String packName,
			Material material, CreativeTabs tab) {
		for (int i = 0; i < 16; i++) {
			if (blockList[i] == null) {
				blockList[i] = (ALColourBlock) new ALColourBlock(material, i)
						.setBlockName("block_" + packName + "_" + i)
						.setCreativeTab(tab).setBlockTextureName(textureName);
			}

			if (stairsList[i] == null) {
				stairsList[i] = (ALColourBlockStairs) new ALColourBlockStairs(
						blockList[i], i).setBlockName(
						"stairs_" + packName + "_" + i).setCreativeTab(tab);
			}
		}
	}

	public static void registerPackBig(String packName) {
		for (int i = 0; i < 16; i++) {
			GameRegistry.registerBlock(blockList[i], "block_" + packName + "_"
					+ i);
			GameRegistry.registerBlock(stairsList[i], "stair_" + packName + "_"
					+ i);
		}
		
		GameRegistry.registerBlock(laddersList[0], "ladder_" + packName);
	}

	public static void registerPackMedium(String packName) {
		for (int i = 0; i < 16; i++) {
			GameRegistry.registerBlock(blockList[i], "block_" + packName + "_"
					+ i);
			GameRegistry.registerBlock(stairsList[i], "stair_" + packName);
		}
	}
}
