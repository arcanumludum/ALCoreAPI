package com.arcanumLudum.ALCore.config.array;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;

import com.arcanumLudum.ALCore.ALCore;

public class ConfigHandler {

	protected Object object;
	protected String name;
	protected File file;
	protected String path;
	
	protected void createFile()
	{
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
			try
			{
				file.createNewFile();
				write(file, object);
			}
			catch(Exception ex)
			{}
		}
	}
	
	protected void write(File file, Object object){}
	
	protected void read(){}
	
	public void modify(String fileName, Object object){}
	
	public void modify(String fileName, String subfolder, Object object){}
	
	public void removeDuplicates(Collection list) 
    {
        HashSet set = new HashSet(list);
        
        list.clear();
        list.addAll(set);
    }
}
