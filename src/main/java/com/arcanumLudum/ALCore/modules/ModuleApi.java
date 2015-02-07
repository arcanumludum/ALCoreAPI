package com.arcanumLudum.ALCore.modules;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ModuleApi 
{
	public static EntityPlayer player = null;
	
	public static int playerX = 0;
	public static int playerY = 0;
	public static int playerZ = 0;

    public void playerTick(PlayerTickEvent event)
    {
    	player = event.player;
    	
    	playerX = (int) player.posX;
    	playerY = (int) player.posY;
    	playerZ = (int) player.posZ;
    }
    
    public void worldTick(WorldTickEvent event)
    {
    	
    }
    
    public void renderTick(RenderTickEvent event)
    {
    	
    }
}
