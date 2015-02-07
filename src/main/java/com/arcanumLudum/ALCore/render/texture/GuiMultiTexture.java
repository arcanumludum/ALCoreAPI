package com.arcanumLudum.ALCore.render.texture;

import net.minecraft.util.ResourceLocation;

import com.arcanumLudum.ALCore.util.exceptions.ResourceLocationRedefinitionException;

public class GuiMultiTexture extends Texture
{
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
	public void bind(int index)
	{
		TextureUtils.bindTexture(this, index);
	}
}
