package com.arcanumLudum.ALCore.render.texture;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import com.arcanumLudum.ALCore.colour.ColourRGB;
import com.arcanumLudum.ALCore.colour.ColourRGBA;
import com.arcanumLudum.ALCore.render.RenderingHelper;

public class TextureEffects 
{
	/**
	 * This blends a texture with a color specified in the arguments. This will disable blending.
	 * 
	 * @param ColourRGBA colour
	 */
	public static void colorTextureOverlay(ColourRGBA colour)
	{
		RenderingHelper.enableBlend(GL11.GL_SRC_COLOR, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL14.glBlendColor(colour.r, colour.g, colour.b, colour.a);
		RenderingHelper.disableBlend();
	}
	
	/**
	 * This blends a texture with a color specified in the arguments. This will disable blending.
	 * 
	 * @param ColourRGB colour
	 */
	public static void colorTextureOverlay(ColourRGB colour)
	{
		RenderingHelper.enableBlend(GL11.GL_SRC_COLOR, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL14.glBlendColor(colour.r, colour.g, colour.b, 1.0f);
		RenderingHelper.disableBlend();
	}
}
