package com.arcanumLudum.ALCore.config.advanced;

import javax.script.Bindings;

public class ConfigHelper 
{
	public void bindClassToEngine(Bindings binds, Object o, String objectName)
	{
		binds.put(objectName, o);
	}
}
