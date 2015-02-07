package com.arcanumLudum.ALCore;

import java.io.File;
import java.io.IOException;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import scala.Console;

import com.arcanumLudum.ALCore.entity.EntityHelper;
import com.arcanumLudum.ALCore.entity.player.PlayerHelper;
import com.arcanumLudum.ALCore.lua.LuaManager;
import com.arcanumLudum.ALCore.modules.loader.ListFiles;
import com.arcanumLudum.ALCore.modules.loader.MainLoader;
import com.arcanumLudum.ALCore.potion.PotionHelper;
import com.arcanumLudum.ALCore.render.GuiHandler;
import com.arcanumLudum.ALCore.tracking.UUIDTracker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@Mod(name = "ALCore", modid = ALCore.MODID, version = ALCore.VERSION)
public class ALCore {
	public static final String MODID = "alcore_api";
	public static final String VERSION = "1.0";

	@Instance("alcore")
	public static ALCore instance;

	public PlayerHelper playerHelper = null;
	public EntityHelper entityHelper = null;
	public PotionHelper potionHelper = null;
	public Minecraft mc = Minecraft.getMinecraft();
	public World world = null;
	public EntityPlayer player = null;
	public GuiHandler guiHandler = new GuiHandler();
	public LuaManager luaManager = new LuaManager();
	protected UUIDTracker uuidTracker = new UUIDTracker();
	private MainLoader mLoader;
	private ListFiles lLoader;	
	
	private boolean toggle = false;
	private boolean initialized = false;
	private String oldMessage = "";
	private int tickCounter;

	public static final String inEclipse = System.getenv("ineclipse");

	@SideOnly(Side.CLIENT)
	public WorldClient world() {
		return Minecraft.getMinecraft().theWorld;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		instance = this;
		File dir;

		if ((inEclipse == null) || (!inEclipse.equals("true"))) {
			try {
				dir = new File(
						Minecraft.getMinecraft().mcDataDir.getCanonicalPath()
								+ "/alc_modules/");

				if (!dir.exists()) {
					Console.out()
							.println(
									"[TestMod] No 'ALC_Modules' folder found, Created one!");
					dir.mkdir();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			mLoader = new MainLoader();
			try {
				mLoader.loadModules(Minecraft.getMinecraft());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
		} else {
			String path = "";

			try {
				path = Minecraft.getMinecraft().mcDataDir.getCanonicalPath()
						.replaceFirst("eclipse", "bin");

				dir = new File(path + "/alc_modules/");

				if (!dir.exists()) {
					Console.out()
							.println(
									"[TestMod] No 'ALC_Modules' folder found, Created one!");

					dir.mkdir();
				} else {
					Console.out().println(
							"[TestMod] Searching 'ALC_Modules' for Modules!");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			lLoader = new ListFiles();
			lLoader.listf(path + "/alc_modules/");
		}
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		Console.out().println("[ALCore] Loading API!");

		FMLCommonHandler.instance().bus().register(this);
		MinecraftForge.EVENT_BUS.register(this);

		if ((inEclipse == null) || (!inEclipse.equals("true"))) {
			Console.out().println(
					"[ALCore] Loaded " + mLoader.moduleCount + " module(s)!");
		} else {
			Console.out().println(
					"[ALCore] Loaded " + lLoader.moduleCount + " module(s)!");
		}

		Console.out().println("[ALCore] Done loading API!");

		playerHelper = new PlayerHelper();
		entityHelper = new EntityHelper();
		potionHelper = new PotionHelper();

		for(Reader tmp : luaManager.readers)
		{
			try {
				if(tmp.getAdvancedConfigFile() != null)
				{
					tmp.getAdvancedConfigFile().readFile(new File(tmp.cfgPath));
				}
				else if(tmp.getLuaFile() != null)
				{
					tmp.getLuaFile().readFile(new File(tmp.luaPath));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		guiHandler.initModules();
	}

	@EventHandler
	public void serverStart(FMLServerStartingEvent event) {
		MinecraftServer server = MinecraftServer.getServer();
		ICommandManager icommand = server.getCommandManager();
		ServerCommandManager commandhandler = ((ServerCommandManager) icommand);
	}

	@SubscribeEvent
	public void tickWorld(WorldTickEvent event) {
		world = event.world;

		if ((inEclipse == null) || (!inEclipse.equals("true"))) {
			mLoader.update(event);
		} else {
			lLoader.update(event);
		}
	}

	@SubscribeEvent
	public void tickPlayer(PlayerTickEvent event) {
		if(event.player.getUniqueID() == mc.thePlayer.getUniqueID())
		{
			tickCounter++;
			
			if (!initialized) {
				player = mc.thePlayer;
				
				for (Reader tmp : LuaManager.instance.readers) {
					tmp.getLuaFile().initBinds();
				}
				
				initialized = true;
			}
			
			guiHandler.updateModules();	
			//uuidTracker.update(player);

			for(Reader tmp : luaManager.readers)
			{
				try {
					tmp.getLuaFile().onChanged(new File(tmp.luaPath));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if ((inEclipse == null) || (!inEclipse.equals("true"))) {
				mLoader.updatePlayer(event);
			} else {
				lLoader.updatePlayer(event);
			}
			
			if (!(ALCore.instance.mc.isSingleplayer())
					&& !(ALCore.instance.mc.isIntegratedServerRunning())) {
				List messages = Minecraft.getMinecraft().ingameGUI.getChatGUI()
						.getSentMessages();
				String lastMessage = "";

				if (messages.size() > 0) {
					lastMessage = (String) messages.get(messages.size() - 1);
				}
			}
		}
	}

	@SubscribeEvent
	public void tickRender(RenderTickEvent event) {
		if ((inEclipse == null) || (!inEclipse.equals("true"))) {
			mLoader.render(event);
		} else {
			lLoader.render(event);
		}

		guiHandler.renderModules();
	}
}
