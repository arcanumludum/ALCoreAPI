package com.arcanumLudum.ALCore.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import com.arcanumLudum.ALCore.entity.monster.EntityALSpider;

public class EntityHelper 
{
	public void getNearestEntity()
	{
		ArrayList<Double> dist = new ArrayList<Double>();
		int entityid;

		for (int i = 0; i < Minecraft.getMinecraft().theWorld.loadedEntityList.size(); i++)
		{
			Entity ent = (Entity) Minecraft.getMinecraft().theWorld.loadedEntityList.get(i);
			List list =  Minecraft.getMinecraft().theWorld.loadedEntityList;
		    	
			double dist1 = ent.getDistanceSqToEntity(ent);
			dist.add(dist1);
			Comparator<String> r = Collections.reverseOrder();
			double minvalue = Collections.min(dist);
			
			if(dist1 == minvalue && Minecraft.getMinecraft().thePlayer.canEntityBeSeen(ent))
			{
				entityid = ent.getEntityId();
			}		
		}
	}
	
	public void changeEntitySpeed(Entity entity, double speed)
	{
		if(entity instanceof EntityALSpider)
		{
			((EntityALSpider)entity).setMovementSpeed(speed);
		}
	}
	
	public void changeEntityJumpheight(Entity entity, double height)
	{
		if(entity instanceof EntityALSpider)
		{
			((EntityALSpider)entity).setJumpHeight(height);
		}
	}
}
