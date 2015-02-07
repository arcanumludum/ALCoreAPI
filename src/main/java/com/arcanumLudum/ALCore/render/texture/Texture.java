package com.arcanumLudum.ALCore.render.texture;

import com.arcanumLudum.ALCore.util.exceptions.IconRedefinitionException;
import com.arcanumLudum.ALCore.util.exceptions.ResourceLocationRedefinitionException;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public abstract class Texture {
	public ResourceLocation[] textureLocArr = new ResourceLocation[96];
	public IIcon[] iconArr = new IIcon[96];
	public int index;

	public void load(String textureLoc, int index) throws ResourceLocationRedefinitionException {};	
	public void load(String textureLoc) throws ResourceLocationRedefinitionException {};
	public void registerIcon(IIconRegister icReg, int index) throws IconRedefinitionException {};
	public IIcon getIcon(int index) { return null; };
	public void bind(int index) {};
	public void bind() {};
}
