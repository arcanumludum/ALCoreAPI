package com.arcanumLudum.ALCore.lua;

import javax.script.Bindings;

public class LuaHandler 
{
	public Bindings bindClassToEngine(Bindings binds, Object o, String objectName)
	{
		binds.put(objectName, o);
		
		return binds;
	}
	
}
