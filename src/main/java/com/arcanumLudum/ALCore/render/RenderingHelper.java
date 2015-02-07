package com.arcanumLudum.ALCore.render;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

import com.arcanumLudum.ALCore.physics.AltAxisAlignedBB;

public class RenderingHelper {

	private static Minecraft mc = Minecraft.getMinecraft();
	private static RenderItem itemRenderer = new RenderItem();

	private static final Sphere sphere = new Sphere();
	{
		sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
	}

	public static void drawSphere(double d1, double d2, double d3, double d4,
			double x, double y, double z, float size, int slices, int stacks,
			float lWidth) {
		enableDefaults();
		GL11.glColor4d(d1, d2, d3, d4);
		GL11.glTranslated(x, y, z);
		GL11.glLineWidth(lWidth);
		sphere.draw(size, slices, stacks);
		disableDefaults();
	}

	public static void drawCheck(int x, int y) {
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glColor4f(0F, 0F, 0.75F, 0.5F);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(2);
		GL11.glBegin(GL11.GL_LINE_STRIP);
		GL11.glVertex2f(x + 1, y + 4);
		GL11.glVertex2f(x + 3, (float) y + 6.5f);
		GL11.glVertex2f(x + 7, y + 2);
		GL11.glEnd();
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
	}

	public static void enableDefaults() {
		Minecraft.getMinecraft().entityRenderer.disableLightmap(1.0D);
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848 /* GL_LINE_SMOOTH */);
		GL11.glPushMatrix();
	}

	public static void disableDefaults() {
		GL11.glPopMatrix();
		GL11.glDisable(2848 /* GL_LINE_SMOOTH */);
		GL11.glDepthMask(true);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		Minecraft.getMinecraft().entityRenderer.enableLightmap(1.0D);
	}

	public void removeDuplicated(Collection list) {
		HashSet set = new HashSet(list);
		list.clear();
		list.addAll(set);
	}

	public static void renderPlayerSphere(double par3, double par5, double par7) {
		float x = (float) par3;
		float y = (float) par5;
		float z = (float) par7;
		renderSphere(x, y, z);
	}

	static Sphere s = new Sphere();

	private static void renderSphere(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 1, z);
		GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(true);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glLineWidth(0.5F);

		s.setDrawStyle(GLU.GLU_LINE);
		float radius = 4.0F;
		s.draw(radius, 32, 32);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glPopMatrix();
	}

	protected float zLevel = 0.0F;

	public static void drawItem(int x, int y, ItemStack stack) {
		itemRenderer.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack,
				x, y);
		itemRenderer.renderItemAndEffectIntoGUI(mc.fontRenderer,
				mc.renderEngine, stack, x, y);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
	}

	public static void drawSmallString(String s, int x, int y, int co) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(s, x * 2,
				y * 2, co);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
	}

	public void drawTexturedModalRect(double i, double d, int par3, int par4,
			double e, double f) {
		float var7 = 0.00390625F;
		float var8 = 0.00390625F;
		Tessellator var9 = Tessellator.instance;
		var9.startDrawingQuads();
		var9.addVertexWithUV((double) (i + 0), (double) (d + f),
				(double) this.zLevel, (double) ((float) (par3 + 0) * var7),
				(double) ((float) (par4 + f) * var8));
		var9.addVertexWithUV((double) (i + e), (double) (d + f),
				(double) this.zLevel, (double) ((float) (par3 + e) * var7),
				(double) ((float) (par4 + f) * var8));
		var9.addVertexWithUV((double) (i + e), (double) (d + 0),
				(double) this.zLevel, (double) ((float) (par3 + e) * var7),
				(double) ((float) (par4 + 0) * var8));
		var9.addVertexWithUV((double) (i + 0), (double) (d + 0),
				(double) this.zLevel, (double) ((float) (par3 + 0) * var7),
				(double) ((float) (par4 + 0) * var8));
		var9.draw();
	}

	public static void drawBorderRect(int x, int y, int x1, int y1, int color,
			int bcolor) {
		float rs = 2f;
		x *= rs;
		y *= rs;
		x1 *= rs;
		y1 *= rs;
		GL11.glScalef(0.5f, 0.5f, 0.5f);
		Gui.drawRect(x + 1, y + 1, x1 - 1, y1 - 1, color);
		// left top & bottom
		Gui.drawRect(x, y, x + 1, y1, bcolor);
		// right top & bottom
		Gui.drawRect(x1 - 1, y, x1, y1, bcolor);
		// top left & right
		Gui.drawRect(x, y, x1, y + 1, bcolor);
		// bottom left & right
		Gui.drawRect(x, y1 - 1, x1, y1, bcolor);
		GL11.glScalef(rs, rs, rs);
	}

	public static void drawMovingString(String s, int height, int displaywidth,
			int color) {
		Integer widthmover = null;
		if ((widthmover == null) || (widthmover.intValue() >= displaywidth + 1)) {
			widthmover = Integer.valueOf(-Minecraft.getMinecraft().fontRenderer
					.getStringWidth(s));
		}
		if (widthmover != null) {
			Minecraft.getMinecraft().fontRenderer.drawString(s,
					widthmover.intValue(), height, color);
			Integer localInteger = widthmover = Integer.valueOf(widthmover
					.intValue() + 1);
		}
	}

	public static void drawRoundedRect(float x, float y, float x1, float y1,
			int borderC, int insideC) {
		x *= 2;
		y *= 2;
		x1 *= 2;
		y1 *= 2;
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		drawVLine(x, y + 1, y1 - 2, borderC);
		drawVLine(x1 - 1, y + 1, y1 - 2, borderC);
		drawHLine(x + 2, x1 - 3, y, borderC);
		drawHLine(x + 2, x1 - 3, y1 - 1, borderC);
		drawHLine(x + 1, x + 1, y + 1, borderC);
		drawHLine(x1 - 2, x1 - 2, y + 1, borderC);
		drawHLine(x1 - 2, x1 - 2, y1 - 2, borderC);
		drawHLine(x + 1, x + 1, y1 - 2, borderC);
		drawRect(x + 1, y + 1, x1 - 1, y1 - 1, insideC);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
	}

	public static void drawBorderedRect(float x, float y, float x1, float y1,
			int borderC, int insideC) {
		x *= 2;
		x1 *= 2;
		y *= 2;
		y1 *= 2;
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		drawVLine(x, y, y1 - 1, borderC);
		drawVLine(x1 - 1, y, y1, borderC);
		drawHLine(x, x1 - 1, y, borderC);
		drawHLine(x, x1 - 2, y1 - 1, borderC);
		drawRect(x + 1, y + 1, x1 - 1, y1 - 1, insideC);
		GL11.glScalef(2.0F, 2.0F, 2.0F);
	}

	public static void drawButton(int x, int y, int x2, int y2, int borderC,
			int topgradient, int bottomgradient) {
		drawBorderedRect(x, y, x2, y2, borderC, 0x00ffffff);
		Gui.drawRect(x2 - 2, y, x2 - 1, y2, 0xff093b5b);
		Gui.drawRect(x + 1, y + 1, x2 - 1, y + 2, 0xff1a587e);
		Gui.drawRect(x + 1, y + 1, x + 2, y2 - 1, 0xff1a587e);
		Gui.drawRect(x, y2 - 2, x2, y2 - 1, 0xff093b5b);
		drawGradientRect(x + 2, y + 2, x2 - 2, y2 - 2, topgradient,
				bottomgradient);
	}

	public static void sendPacket(Packet p) {
		mc.thePlayer.sendQueue.addToSendQueue(p);
	}

	public static boolean stringListContains(List<String> list, String needle) {
		for (String s : list) {
			if (s.trim().equalsIgnoreCase(needle.trim())) {
				return true;
			}
		}
		return false;
	}

	public static void drawBorderedRect(double x, double y, double x2,
			double y2, float l1, int col1, int col2) {
		drawRect((float) x, (float) y, (float) x2, (float) y2, col2);

		float f = (float) (col1 >> 24 & 0xFF) / 255F;
		float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
		float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
		float f3 = (float) (col1 & 0xFF) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);

		GL11.glPushMatrix();
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glLineWidth(l1);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	public static void drawHLine(float par1, float par2, float par3, int par4) {
		if (par2 < par1) {
			float var5 = par1;
			par1 = par2;
			par2 = var5;
		}

		drawRect(par1, par3, par2 + 1, par3 + 1, par4);
	}

	public static void drawVLine(float par1, float par2, float par3, int par4) {
		if (par3 < par2) {
			float var5 = par2;
			par2 = par3;
			par3 = var5;
		}

		drawRect(par1, par2 + 1, par1 + 1, par3, par4);
	}

	public static void drawRect(float paramXStart, float paramYStart,
			float paramXEnd, float paramYEnd, int paramColor) {
		float alpha = (float) (paramColor >> 24 & 0xFF) / 255F;
		float red = (float) (paramColor >> 16 & 0xFF) / 255F;
		float green = (float) (paramColor >> 8 & 0xFF) / 255F;
		float blue = (float) (paramColor & 0xFF) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);

		GL11.glPushMatrix();
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2d(paramXEnd, paramYStart);
		GL11.glVertex2d(paramXStart, paramYStart);
		GL11.glVertex2d(paramXStart, paramYEnd);
		GL11.glVertex2d(paramXEnd, paramYEnd);
		GL11.glEnd();
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	public static void drawGradientRect(double x, double y, double x2,
			double y2, int col1, int col2) {
		float f = (float) (col1 >> 24 & 0xFF) / 255F;
		float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
		float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
		float f3 = (float) (col1 & 0xFF) / 255F;

		float f4 = (float) (col2 >> 24 & 0xFF) / 255F;
		float f5 = (float) (col2 >> 16 & 0xFF) / 255F;
		float f6 = (float) (col2 >> 8 & 0xFF) / 255F;
		float f7 = (float) (col2 & 0xFF) / 255F;

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y);
		GL11.glColor4f(1, 1, 1, 1);
		
		GL11.glColor4f(f5, f6, f7, f4);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	public static void drawGradientBorderedRect(double x, double y, double x2,
			double y2, float l1, int col1, int col2, int col3) {
		float f = (float) (col1 >> 24 & 0xFF) / 255F;
		float f1 = (float) (col1 >> 16 & 0xFF) / 255F;
		float f2 = (float) (col1 >> 8 & 0xFF) / 255F;
		float f3 = (float) (col1 & 0xFF) / 255F;

		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_BLEND);

		GL11.glPushMatrix();
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glLineWidth(1F);
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y);
		GL11.glVertex2d(x2, y);
		GL11.glVertex2d(x, y2);
		GL11.glVertex2d(x2, y2);
		GL11.glEnd();
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glPopMatrix();

		drawGradientRect(x, y, x2, y2, col2, col3);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
	}

	public static void drawStrip(int x, int y, float width, double angle,
			float points, float radius, int color) {
		GL11.glPushMatrix();
		float f1 = (float) (color >> 24 & 255) / 255.0F;
		float f2 = (float) (color >> 16 & 255) / 255.0F;
		float f3 = (float) (color >> 8 & 255) / 255.0F;
		float f4 = (float) (color & 255) / 255.0F;
		GL11.glTranslatef(x, y, 0);
		GL11.glColor4f(f2, f3, f4, f1);
		GL11.glLineWidth(width);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glEnable(GL13.GL_MULTISAMPLE);

		if (angle > 0) {
			GL11.glBegin(GL11.GL_LINE_STRIP);

			for (int i = 0; i < angle; i++) {
				float a = (float) (i * (angle * Math.PI / points));
				float xc = (float) (Math.cos(a) * radius);
				float yc = (float) (Math.sin(a) * radius);
				GL11.glVertex2f(xc, yc);
			}

			GL11.glEnd();
		}

		if (angle < 0) {
			GL11.glBegin(GL11.GL_LINE_STRIP);

			for (int i = 0; i > angle; i--) {
				float a = (float) (i * (angle * Math.PI / points));
				float xc = (float) (Math.cos(a) * -radius);
				float yc = (float) (Math.sin(a) * -radius);
				GL11.glVertex2f(xc, yc);
			}

			GL11.glEnd();
		}

		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDisable(GL13.GL_MULTISAMPLE);
		GL11.glDisable(GL11.GL_MAP1_VERTEX_3);
		GL11.glPopMatrix();
	}

	public static void drawCircle(float cx, float cy, float r,
			int num_segments, int c) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		r *= 2;
		cx *= 2;
		cy *= 2;
		float f = (float) (c >> 24 & 0xff) / 255F;
		float f1 = (float) (c >> 16 & 0xff) / 255F;
		float f2 = (float) (c >> 8 & 0xff) / 255F;
		float f3 = (float) (c & 0xff) / 255F;
		float theta = (float) (2 * 3.1415926 / (num_segments));
		float p = (float) Math.cos(theta);// calculate the sine and cosine
		float s = (float) Math.sin(theta);
		float t;
		GL11.glColor4f(f1, f2, f3, f);
		float x = r;
		float y = 0;// start at angle = 0
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glBegin(GL11.GL_LINE_LOOP);
		for (int ii = 0; ii < num_segments; ii++) {
			GL11.glVertex2f(x + cx, y + cy);// final vertex vertex

			// rotate the stuff
			t = x;
			x = p * x - s * y;
			y = s * t + p * y;
		}
		GL11.glEnd();
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glScalef(2F, 2F, 2F);
	}

	public static void drawFullCircle(int cx, int cy, double r, int c) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		r *= 2;
		cx *= 2;
		cy *= 2;
		float f = (float) (c >> 24 & 0xff) / 255F;
		float f1 = (float) (c >> 16 & 0xff) / 255F;
		float f2 = (float) (c >> 8 & 0xff) / 255F;
		float f3 = (float) (c & 0xff) / 255F;
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glBlendFunc(770, 771);
		GL11.glColor4f(f1, f2, f3, f);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		for (int i = 0; i <= 360; i++) {
			double x = Math.sin((i * Math.PI / 180)) * r;
			double y = Math.cos((i * Math.PI / 180)) * r;
			GL11.glVertex2d(cx + x, cy + y);
		}
		GL11.glEnd();
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(3553);
		GL11.glDisable(3042);
		GL11.glScalef(2F, 2F, 2F);
	}

	public static void drawOutlinedBoundingBox(
			AltAxisAlignedBB par1AxisAlignedBB) {
		Tessellator var2 = Tessellator.instance;
		var2.startDrawing(3);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.minZ);
		var2.draw();
		var2.startDrawing(3);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.minZ);
		var2.draw();
		var2.startDrawing(1);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.minZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY,
				par1AxisAlignedBB.maxZ);
		var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY,
				par1AxisAlignedBB.maxZ);
		var2.draw();
	}

	public static void drawBoundingBox(AltAxisAlignedBB axisalignedbb) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.minZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY,
				axisalignedbb.maxZ);
		tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY,
				axisalignedbb.maxZ);
		tessellator.draw();
	}

	public static void drawESP(double d, double d1, double d2, double r,
			double b, double g) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(1.5F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glColor4d(r, g, b, 0.1825F);
		drawBoundingBox(new AltAxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0,
				d2 + 1.0));
		GL11.glColor4d(r, g, b, 1.0F);
		drawOutlinedBoundingBox(new AltAxisAlignedBB(d, d1, d2, d + 1.0,
				d1 + 1.0, d2 + 1.0));
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public static void startDrawingESPs(double d, double d1, double d2,
			double r, double g, double b) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(1.5F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glColor4d(r, g, b, 0.185F);
		drawBoundingBox(new AltAxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0,
				d2 + 1.0));
		GL11.glColor4d(r, g, b, 1.0F);
		drawOutlinedBoundingBox(new AltAxisAlignedBB(d, d1, d2, d + 1.0,
				d1 + 1.0, d2 + 1.0));
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}

	public static void startDrawingESPs(AltAxisAlignedBB bb, float r, float b,
			float g) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(1.5F);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glColor4d(r, b, g, 0.185F);
		drawBoundingBox(bb);
		GL11.glColor4d(r, b, g, 1.0F);
		drawOutlinedBoundingBox(bb);
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}
	
	public static void enableBlend(int f1, int f2)
	{
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(f1, f2);
	}
	
	public static void disableBlend()
	{
		GL11.glDisable(GL11.GL_BLEND);
	}
}
