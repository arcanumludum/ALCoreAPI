package com.arcanumLudum.ALCore.render.texture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.arcanumLudum.ALCore.colour.Colour;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TextureUtils {
	public static TextureManager getEngine() {
		return Minecraft.getMinecraft().renderEngine;
	}

	public static void bindAtlas(int index) {
		getEngine().bindTexture(
				index == 0 ? TextureMap.locationBlocksTexture
						: TextureMap.locationItemsTexture);
	}

	public static IIcon safeIcon(IIcon icon) {
		if (icon == null)
			icon = ((TextureMap) Minecraft.getMinecraft().getTextureManager()
					.getTexture(TextureMap.locationBlocksTexture))
					.getAtlasSprite("missingno");

		return icon;
	}
	
	public static void bindTexture(Texture texture, int index)
	{
		getEngine().bindTexture(texture.textureLocArr[index]);
	}
}
