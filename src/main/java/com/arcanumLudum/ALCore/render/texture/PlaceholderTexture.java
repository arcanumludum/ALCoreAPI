package com.arcanumLudum.ALCore.render.texture;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.arcanumLudum.ALCore.util.exceptions.IconRedefinitionException;
import com.arcanumLudum.ALCore.util.exceptions.ResourceLocationRedefinitionException;

public class PlaceholderTexture extends Texture {
	@Override
	public void load(String textureLoc, int index)
			throws ResourceLocationRedefinitionException {
		if (this.textureLocArr[index] == null) {
			this.textureLocArr[index] = new ResourceLocation(textureLoc);
		} else {
			throw new ResourceLocationRedefinitionException(
					"The ResourceLocation slot at index (" + index
							+ ") is in use.");
		}
	}

	@Override
	public void registerIcon(IIconRegister icReg, int index)
			throws IconRedefinitionException {
		if (this.iconArr[index] == null) {
			this.iconArr[index] = icReg.registerIcon(this.textureLocArr[index]
					.getResourceDomain()
					+ ":"
					+ this.textureLocArr[index].getResourcePath());
		} else {
			throw new IconRedefinitionException("The Icon slot at index ("
					+ index + ") is in use.");
		}
	}

	@Override
	public IIcon getIcon(int index) {
		return this.iconArr[index];
	}
}
