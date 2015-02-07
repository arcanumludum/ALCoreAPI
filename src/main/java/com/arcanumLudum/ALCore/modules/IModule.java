package com.arcanumLudum.ALCore.modules;

import java.io.IOException;

import net.minecraft.client.Minecraft;

public class IModule extends ModuleApi 
{
	public String moduleName = "";
	
	public IModule(String name)
	{
		moduleName = name;
	}
	
    public ModuleConfig getSuggestedConfigFileForModule()
    {
    	String path = "";
    	
		try {
			path = Minecraft.getMinecraft().mcDataDir.getCanonicalPath() + "/al_modules/config/" + moduleName.toLowerCase() + ".cfg";
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return new ModuleConfig(path);
    }
}
