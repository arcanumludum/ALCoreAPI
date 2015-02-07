package com.arcanumLudum.ALCore.modules;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ModuleConfig 
{
	private Configuration config = null;
	
	public ModuleConfig(String path)
	{
		config = new Configuration(new File(path));
	}
	
	public void loadConfig()
	{
		config.load();
	}
	
	public void saveConfig()
	{
		config.save();
	}
	
	public Configuration getConfig()
	{
		return config;
	}
	
}
