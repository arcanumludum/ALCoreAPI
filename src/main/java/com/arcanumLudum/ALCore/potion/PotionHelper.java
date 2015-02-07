package com.arcanumLudum.ALCore.potion;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.arcanumLudum.ALCore.entity.player.PlayerHelper;

public class PotionHelper 
{	
	public static PotionHelper instance = new PotionHelper();
	private EntityLivingBase elb;
	
	public PotionHelper(){}
	
	public void setPotionEffectToPlayer(int id, int time, int amplifier)
	{
		PlayerHelper.player.addPotionEffect(new PotionEffect(id, time, amplifier));
	}
	
	public void removePotionEffectFromPlayer(int id)
	{
		PlayerHelper.player.removePotionEffect(id);
	}
	
	
	/**
	 * Example: getIfPlayerSelectedPotionEffect(Potion.blindness);
	 * returns the potion effect if it's active, if not it just returns null.
	 * @param potion
	 */
	public void getIfPlayerSelectedPotionEffect(Potion potion)
	{
		Minecraft.getMinecraft().thePlayer.getActivePotionEffect(potion);
	}
}
