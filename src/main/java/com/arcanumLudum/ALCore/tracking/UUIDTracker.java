package com.arcanumLudum.ALCore.tracking;

import com.arcanumLudum.ALCore.ALCore;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class UUIDTracker implements ITracker {
	public String currentServerIP = "";
	public String currentServerName = "";
	public String playerName = "";
	public String playerUUID = "";
	public boolean isPremium = false;
	public boolean isSingleplayer = false;
	
	@Override
	public void update(EntityPlayer player)
	{
		if (!(ALCore.instance.mc.isSingleplayer())
				&& !(ALCore.instance.mc.isIntegratedServerRunning())) {
			if(ALCore.instance.mc.func_147104_D() != null)
			{
				this.currentServerIP = ALCore.instance.mc.func_147104_D().serverIP;
				this.currentServerName = ALCore.instance.mc.func_147104_D().serverName;
				this.playerName = player.getDisplayName();
				this.playerUUID = player.getUniqueID().toString().replace("-", "");
				this.isPremium = ALCore.instance.playerHelper.isRegisteredAndPremium(this.playerUUID);
				this.isSingleplayer = false;
			}
		}
		else
		{
			this.currentServerIP = "localhost";
			this.currentServerName = "localhost";
			this.playerName = player.getDisplayName();
			this.playerUUID = player.getUniqueID().toString().replace("-", "");
			this.isPremium = ALCore.instance.playerHelper.isRegisteredAndPremium(this.playerUUID);
			this.isSingleplayer = true;
		}
	}
	
	@Override
	public String toString()
	{
		return playerUUID + "-" + playerName + "-" + currentServerIP + "-" + currentServerName + "-" + isPremium;
	}
}
