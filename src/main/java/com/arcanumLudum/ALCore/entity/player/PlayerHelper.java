package com.arcanumLudum.ALCore.entity.player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;

import com.arcanumLudum.ALCore.ALCore;
import com.arcanumLudum.ALCore.entity.NewEntity;
import com.mojang.authlib.GameProfile;

/**
 * Helps modders in the process of finding entity objects, doesn't change any
 * mechanic but uses existent ones.
 * 
 * @author Kodehawa
 * @version 1.0.0a - MC 1.7.10 Part of ALCore (c) Kodehawa, MrPonyCaptain 2014
 */
public class PlayerHelper {

	public NewEntity newplayer;
	private float speed;
	private float flyspeed;
	private LocationHelper location;
	public static EntityPlayer player = ALCore.instance.player;

	/**
	 * Sets how fast walks the player, not sure if working after 1.5.2
	 * 
	 * @param speed
	 */
	public void setPlayerSpeed(float speed) {
		ALCore.instance.player.capabilities
				.setPlayerWalkSpeed(speed);

		this.speed = speed;
	}

	public float getPlayerSpeed() {
		return speed;
	}

	public void setPlayerJump() {
		ALCore.instance.player.jump();
	}

	public void setPlayerRunning(boolean run) {
		ALCore.instance.player.setSprinting(run);
	}

	/**
	 * Sets player step height (how far it can jumps blocks without pressing
	 * space, 0-256
	 * 
	 * @param stepheight
	 */
	public void setPlayerSH(float sh) {
		ALCore.instance.player.stepHeight = sh;
	}

	public float getPlayerSH() {
		return ALCore.instance.player.stepHeight;
	}

	/**
	 * Sends the player capabilities to the minecraft server, this is nessesary
	 * for apply changes when you set capabilities to a player.
	 */
	public void sendPlayerCapabilities() {
		ALCore.instance.player.sendPlayerAbilities();
	}

	/**
	 * Sets how fast can the player fly in creative.
	 * 
	 * @param fs
	 */
	public void setPlayerFlySpeed(float fs) {
		ALCore.instance.player.capabilities.setFlySpeed(fs);
		this.flyspeed = fs;
	}

	public float getPlayerFlySpeed() {
		return ALCore.instance.player.capabilities.getFlySpeed();
	}

	public void setPlayerSneak(boolean s) {
		ALCore.instance.mc.thePlayer.movementInput.sneak = s;
		ALCore.instance.player.setSneaking(s);
	}

	public boolean getPlayerSneak() {
		return ALCore.instance.player.isSneaking();
	}

	public GameProfile getPlayerProfile() {
		return ALCore.instance.player.getGameProfile();
	}

	/**
	 * If you set this to true, player will be able to fly even in survival
	 * more. Already sends the capabilities to the server so there is no need to
	 * use .setPlayerCapabilities();
	 * 
	 * @param canfly
	 */
	public void setPlayerFly(boolean canfly) {
		ALCore.instance.player.capabilities.allowFlying = canfly;
		this.sendPlayerCapabilities();
	}

	public void setPlayerFlying(boolean k)
	{
		ALCore.instance.player.capabilities.isFlying = k;
	}
	
	/**
	 * Is player flying?
	 * 
	 * @return true or false
	 */
	public boolean getPlayerFly() {
		return ALCore.instance.player.capabilities.isFlying;
	}

	/**
	 * Sets player level.
	 * 
	 * @param score
	 */
	public void setPlayerLevel(int level) {
		ALCore.instance.player.addExperienceLevel(level);
	}

	/**
	 * Sets the player health (can heal player)
	 * 
	 * @param playerhealth
	 */
	public void setPlayerHealth(int playerhealth) {
		ALCore.instance.player.setHealth(playerhealth);
	}

	public float getPlayerHealth() {
		return ALCore.instance.player.getHealth();
	}

	/**
	 * Sends player to the specified dimension.
	 * 
	 * @param dimension
	 */
	public void sendPlayerToDimension(int dimension) {
		ALCore.instance.player.travelToDimension(dimension);
	}

	/**
	 * Kills the player with an specified damage source.
	 * 
	 * @param damagesource
	 */
	public void killPlayer(DamageSource damagesource) {
		ALCore.instance.player
				.attackEntityFrom(damagesource, 20.0F);
	}

	/**
	 * Kills the player without an specified damage source (generic).
	 * 
	 * @param damagesource
	 */
	public void killPlayer() {
		ALCore.instance.player.attackEntityFrom(
				DamageSource.generic, 20.0F);
	}

	/**
	 * Damages the player with an specified damage source and damage amount.
	 * 
	 * @param damagesource
	 */
	public void damagePlayer(DamageSource damagesource, int damageamount) {
		ALCore.instance.player.attackEntityFrom(damagesource,
				damageamount);
	}

	/**
	 * Is player dead?
	 */
	public boolean isPlayerDead() {
		return ALCore.instance.player.isDead;
	}

	/**
	 * Is player in creative mode?
	 */
	public boolean isPlayerCreative() {
		return ALCore.instance.player.capabilities.isCreativeMode;
	}

	/**
	 * Creates a copy of the current player, just for fun~
	 */
	public void createCopyPlayer() {
		location = new LocationHelper(ALCore.instance.player);
		NewEntity e = new NewEntity(ALCore.instance.world(),
				ALCore.instance.player.getGameProfile());
		e.setPositionAndRotation(location.posX, location.posY - 1.5,
				location.posZ, location.rotationYaw, location.rotationPitch);
		
		ALCore.instance.world().addEntityToWorld(-1, e);
		newplayer = e;
	}

	/**
	 * Gets location with some better precision.
	 * 
	 * @author Kodehawa
	 */
	class LocationHelper {
		public double posX;
		public double posY;
		public double posZ;
		public float rotationYaw;
		public float rotationPitch;
		public String name;

		@Override
		public LocationHelper clone() {
			return new LocationHelper(posX, posY, posZ, rotationYaw,
					rotationPitch, name);
		}

		public LocationHelper(Entity entity) {
			this(entity, "");
		}

		public LocationHelper(Entity entity, String s) {
			this(entity.posX, entity.posY, entity.posZ, entity.rotationYaw,
					entity.rotationPitch, s);
		}

		public LocationHelper() {
			this(0.0D, 0.0D, 0.0D, 0.0F, 0.0F, "");
		}

		public LocationHelper(double d, double d1, double d2, String s) {
			this(d, d1, d2, 0.0F, 0.0F, s);
		}

		public LocationHelper(double d, double d1, double d2) {
			this(d, d1, d2, 0.0F, 0.0F, "");
		}

		public LocationHelper(double d, double d1, double d2, float f, float f1) {
			this(d, d1, d2, f, f1, "");
		}

		public LocationHelper(double d, double d1, double d2, float f,
				float f1, String s) {
			posX = d;
			posY = d1;
			posZ = d2;
			rotationYaw = f;
			rotationPitch = f1;
			name = s;
		}

		public double distance(LocationHelper Location) {
			return Math.sqrt(distanceSquare(Location));
		}

		public double distanceSquare(LocationHelper Location) {
			double d = Location.posX - posX;
			double d1 = Location.posY - posY;
			double d2 = Location.posZ - posZ;
			return d * d + d1 * d1 + d2 * d2;
		}

		public double distance2D(LocationHelper Location) {
			return Math.sqrt(distance2DSquare(Location));
		}

		public double distance2DSquare(LocationHelper Location) {
			double d = Location.posX - posX;
			double d1 = Location.posZ - posZ;
			return d * d + d1 * d1;
		}

		public double distanceY(LocationHelper Location) {
			return Location.posY - posY;
		}

		public LocationHelper(String s) throws Exception {
			String as[] = s.split(";", 6);
			if (as.length != 6) {
				throw new Exception("Invalid line!");
			} else {
				name = as[5];
				posX = Double.parseDouble(as[0]);
				posY = Double.parseDouble(as[1]);
				posZ = Double.parseDouble(as[2]);
				rotationYaw = Float.parseFloat(as[3]);
				rotationPitch = Float.parseFloat(as[4]);
				return;
			}
		}

		public String export() {
			return (new StringBuilder()).append(posX).append(";").append(posY)
					.append(";").append(posZ).append(";").append(rotationYaw)
					.append(";").append(rotationPitch).append(";").append(name)
					.toString();
		}
	}
	
	public boolean isRegisteredAndPremium() {
		URL u = null;

		try {
			u = new URL(
					"http://arcanumludum.com/includes/functions/minecraft/CheckRegisterPremium.php?uuid="
							+ this.player.getUniqueID().toString().replace("-", ""));
			URLConnection c = u.openConnection();
			InputStream r = c.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(r));
			
			return Boolean.parseBoolean(reader.readLine());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean isRegisteredAndPremium(String UUID) {
		URL u = null;

		try {
			u = new URL(
					"http://arcanumludum.com/includes/functions/minecraft/CheckRegisterPremium.php?uuid="
							+ UUID.replace("-", ""));
			URLConnection c = u.openConnection();
			InputStream r = c.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(r));
			
			return Boolean.parseBoolean(reader.readLine());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}
}
