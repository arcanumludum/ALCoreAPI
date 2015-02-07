package com.arcanumLudum.ALCore.render;

import net.minecraftforge.common.MinecraftForge;

public class GuiHandler 
{
	public IGuiModule[] modules = new IGuiModule[100000];
	
	public int addGuiModule(int i, IGuiModule module)
	{
		if(modules[i] == null)
		{
			modules[i] = module;
			
			return i;
		}
		else
		{
			addGuiModule(i++, module);
		}
		
		return -1;
	}
	
	public IGuiModule getGuiModule(int i)
	{
		if(modules[i] != null)
		{
			return modules[i];
		}
		else
		{
			return null;
		}
	}
	
	public int deleteGuiModule(int i)
	{
		if(modules[i] != null)
		{
			modules[i] = null;
			
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public void initModules()
	{
		for(IGuiModule tmp : modules)
		{
			if(tmp != null)
			{
				MinecraftForge.EVENT_BUS.register(tmp);
						
				tmp.init();
			}
		}
	}
	
	public void renderModules()
	{
		for(IGuiModule tmp : modules)
		{
			if(tmp != null)
			{
				if(!tmp.isClosed)
				{
					tmp.render();
				}
			}
		}
	}
	
	public void updateModules()
	{
		for(IGuiModule tmp : modules)
		{
			if(tmp != null)
			{
				if(!tmp.isClosed)
				{
					tmp.update();
				}
			}
		}
	}
}
