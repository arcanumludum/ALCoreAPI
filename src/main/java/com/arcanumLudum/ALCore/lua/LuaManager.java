package com.arcanumLudum.ALCore.lua;

import java.util.ArrayList;

import com.arcanumLudum.ALCore.Reader;

public class LuaManager 
{
	public ArrayList<Reader> readers = new ArrayList<Reader>();
	public static LuaManager instance;

	public LuaManager()
	{
		instance = this;
	}
	
	public void addReader(Reader reader)
	{
		readers.add(reader);
	}
	
	public Reader getReader(int index)
	{
		return readers.get(index);
	}
	
	public Reader getReaderByPath(String path)
	{
		for(Reader tmp : readers)
		{
			if(tmp.luaPath.equals(path) || tmp.cfgPath.equals(path))
			{				
				return readers.get(readers.indexOf(tmp));
			}
			else
			{
				return null;
			}
		}
		
		return null;
	}
}
