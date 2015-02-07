package com.arcanumLudum.ALCore;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import net.minecraft.client.Minecraft;

import com.arcanumLudum.ALCore.config.advanced.ALCode;
import com.arcanumLudum.ALCore.lua.ScriptEngineLua;

public class Reader 
{
	private BufferedReader bufferedReader = null;
	private ALCode cfgFile = null;
	private ScriptEngineLua luaFile = null;	
	
	public String luaPath = "";
	public String cfgPath = "";
	
	public Reader createAdvancedConfigFile(String path)
	{
		try
		{
			cfgPath = ALCore.instance.mc.mcDataDir.getCanonicalPath() + File.separator + "ALCore" + File.separator + "config";
			
			File file = new File(cfgPath);
			file.mkdirs();
			
			cfgPath = cfgPath + File.separator + path + ".js";
			file = new File(cfgPath);
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileInputStream imputStream = new FileInputStream(cfgPath);
			DataInputStream dataStream = new DataInputStream(imputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(dataStream));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		cfgFile = new ALCode(bufferedReader);
		
		return this;
	}
	
	public Reader createLuaFileCustom(String folder, String path)
	{		
		try
		{
			String luaPathTMP = Minecraft.getMinecraft().mcDataDir.getCanonicalPath() + File.separator + folder + File.separator + "lua";
			
			File file = new File(luaPathTMP);
			file.mkdirs();
			
			luaPathTMP = luaPathTMP + File.separator + path + ".lua";
			
			file = new File(luaPathTMP);
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileInputStream imputStream = new FileInputStream(luaPathTMP);
			DataInputStream dataStream = new DataInputStream(imputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(dataStream));
			
			this.luaPath = luaPathTMP;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		this.luaFile = new ScriptEngineLua(bufferedReader, this.luaPath, path);
		
		return this;
	}
	
	public Reader createLuaFile(String path)
	{		
		try
		{
			luaPath = ALCore.instance.mc.mcDataDir.getCanonicalPath() + File.separator + "ALCore" + File.separator + "lua";
			
			File file = new File(luaPath);
			file.mkdirs();
			
			luaPath = luaPath + File.separator + path + ".lua";
			file = new File(luaPath);
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			FileInputStream imputStream = new FileInputStream(luaPath);
			DataInputStream dataStream = new DataInputStream(imputStream);
			bufferedReader = new BufferedReader(new InputStreamReader(dataStream));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		luaFile = new ScriptEngineLua(bufferedReader, this.luaPath, path);
		
		return this;
	}
	
	public ScriptEngineLua getLuaFile()
	{
		return luaFile;
	}
	
	public ALCode getAdvancedConfigFile()
	{
		return cfgFile;
	}
	
	public BufferedReader createReader(String path)
	{
		FileInputStream imputStream = null;
		
		try {
			imputStream = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		DataInputStream dataStream = new DataInputStream(imputStream);
		bufferedReader = new BufferedReader(new InputStreamReader(dataStream));
		
		return bufferedReader;
	}
}
