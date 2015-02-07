package com.arcanumLudum.ALCore.modules;

import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class MainSuper 
{
	public void initLoad() {}	
	public void registerObjects() {}
	public void playerTick(PlayerTickEvent event) {}
	public void worldTick(WorldTickEvent event) {}
	public void renderTick(RenderTickEvent event) {}
}
