package com.arcanumLudum.ALCore.lua;

import javax.script.Bindings;

public class LuaHelper 
{
	public String toLocal(String file)
	{
		if(file.contains(".lua"))
		{
			file = file.replace(".lua", "");
			
			return "ALCore.lua." + file;
		}
		else
		{
			return "ALCore.lua." + file;
		}
	}
	
}
