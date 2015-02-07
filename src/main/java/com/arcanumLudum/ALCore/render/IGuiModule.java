package com.arcanumLudum.ALCore.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class IGuiModule extends Gui
{
	public boolean isClosed = true;
	public String moduleName = "";
	
	public void init() {}
	public void render() {};
	public void update() {};
	
	public void open()
	{
		if(isClosed)
		{
			isClosed = false;
		}
	}
	
	public void close()
	{
		if(!isClosed)
		{
			isClosed = true;
		}
	}
	
	public static void drawTexturedRect(ResourceLocation texture, double x, double y, int u, int v, int width, int height, int imageWidth, int imageHeight, double scale) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        double minU = (double)u / (double)imageWidth;
        double maxU = (double)(u + width) / (double)imageWidth;
        double minV = (double)v / (double)imageHeight;
        double maxV = (double)(v + height) / (double)imageHeight;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x + scale*(double)width, y + scale*(double)height, 0, maxU, maxV);
        tessellator.addVertexWithUV(x + scale*(double)width, y, 0, maxU, minV);
        tessellator.addVertexWithUV(x, y, 0, minU, minV);
        tessellator.addVertexWithUV(x, y + scale*(double)height, 0, minU, maxV);
        tessellator.draw();
    }
	
	public static String addAlpha(String originalColor, double alpha) {
	    long alphaFixed = Math.round(alpha * 255);
	    String alphaHex = Long.toHexString(alphaFixed);
	    if (alphaHex.length() == 1) {
	        alphaHex = "0" + alphaHex;
	    }
	    
	    originalColor = originalColor.replace("#", "#" + alphaHex);

	    return originalColor;
	}
}
