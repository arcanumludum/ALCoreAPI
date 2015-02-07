package com.arcanumLudum.ALCore.render.texture;

import net.minecraft.util.ResourceLocation;

import com.arcanumLudum.ALCore.util.exceptions.ResourceLocationRedefinitionException;

public class GuiTexture extends Texture
{
	@Override
	public void load(String textureLoc)
			throws ResourceLocationRedefinitionException {
		if (this.textureLocArr[0] == null) {
			this.textureLocArr[0] = new ResourceLocation(textureLoc);
		} else {
			throw new ResourceLocationRedefinitionException(
					"The ResourceLocation slot at index (" + 0
							+ ") is in use.");
		}
	}
	
	@Override
	public void bind()
	{
		TextureUtils.bindTexture(this, 0);
	}
}
