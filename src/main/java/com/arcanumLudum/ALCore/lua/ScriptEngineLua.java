package com.arcanumLudum.ALCore.lua;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import org.luaj.vm2.LuaError;

import com.arcanumLudum.ALCore.ALCore;
import com.arcanumLudum.ALCore.Reader;
import com.arcanumLudum.ALCore.entity.monster.EntityALSpider;
import com.arcanumLudum.ALCore.math.MathHelper;
import com.arcanumLudum.ALCore.modules.IModule;
import com.arcanumLudum.ALCore.modules.MainSuper;
import com.arcanumLudum.ALCore.modules.ModuleApi;
import com.arcanumLudum.ALCore.modules.ModuleConfig;
import com.arcanumLudum.ALCore.render.IGuiModule;
import com.arcanumLudum.ALCore.render.RenderingHelper;

public class ScriptEngineLua {
	private BufferedReader fileReader = null;
	private ScriptEngine se = null;
	private ScriptEngineFactory sef = null;
	private CompiledScript cs;
	private String path = "";
	private String file = "";

	public LuaHelper helper;
	public LuaHandler handler;
	public Bindings bindings = null;
	public String input;

	private long oldTime;
	private long newTime;
	
	public ScriptEngineLua(BufferedReader reader, String _path, String _file) {
		fileReader = reader;
		path = _path;
		file = _file;

		helper = new LuaHelper();
		handler = new LuaHandler();

		se = new ScriptEngineManager().getEngineByName("luaj");
		sef = se.getFactory();
	}

	public void initBinds() {
		bindings = se.createBindings();
		bindings = handler.bindClassToEngine(bindings, System.out, "stdOut");
		bindings = handler.bindClassToEngine(bindings, helper, "luaHelper");
		bindings = handler.bindClassToEngine(bindings, ALCore.instance.player,
				"player");
		bindings = handler.bindClassToEngine(bindings, ALCore.instance.mc,
				"minecraft");
		bindings = handler.bindClassToEngine(bindings, ALCore.instance.world,
				"world");
		bindings = handler.bindClassToEngine(bindings, ALCore.instance.world(),
				"worldClient");
		bindings = handler.bindClassToEngine(bindings, Block.class, "Block");
		bindings = handler.bindClassToEngine(bindings, Blocks.class, "Blocks");
		bindings = handler.bindClassToEngine(bindings, EntitySheep.class,
				"EntitySheep");
		bindings = handler.bindClassToEngine(bindings, Entity.class, "Entity");
		bindings = handler.bindClassToEngine(bindings, Gui.class, "Gui");
		bindings = handler.bindClassToEngine(bindings, GuiScreen.class,
				"GuiScreen");
		bindings = handler.bindClassToEngine(bindings, MainSuper.class,
				"MainSuper");
		bindings = handler.bindClassToEngine(bindings, IModule.class, "IModule");
		bindings = handler.bindClassToEngine(bindings, ModuleApi.class,
				"ModuleApi");
		bindings = handler.bindClassToEngine(bindings, ModuleConfig.class,
				"ModuleConfig");
		bindings = handler.bindClassToEngine(bindings, IGuiModule.class,
				"IGuiModule");
		bindings = handler.bindClassToEngine(bindings, RenderingHelper.class,
				"RenderingHelper");
		bindings = handler.bindClassToEngine(bindings, MathHelper.class,
				"MathHelper");
		bindings = handler.bindClassToEngine(bindings, EntityALSpider.class,
				"EntityALSpider");
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.guiHandler, "guiHandler");
		bindings = handler.bindClassToEngine(bindings, ALCore.instance, "alCore");
		/*bindings = handler.bindClassToEngine(bindings, ALCore.instance.luaManager,
				"luaManager");*/
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.potionHelper, "potionHelper");
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.entityHelper, "entityHelper");
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.playerHelper, "playerHelper");
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.mc.getSoundHandler(), "soundHandler");
		bindings = handler.bindClassToEngine(bindings,
				ALCore.instance.mc.getTextureManager(), "textureManager");
	}

	/*
	 * Reads and saves the content of the config file.
	 */
	public void readFile(File file) throws IOException
	{		
		oldTime = file.lastModified();
		fileReader = new Reader().createReader(path);
		
		String content = "";
		
		try
		{			
			String s;
			
			while((s = fileReader.readLine()) != null){
				content += s + "\n";
			}
			
			fileReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			input = content;
			
			try {
				cs = ((Compilable)se).compile(content);
			} catch (ScriptException e) {
				if(ALCore.instance.player != null)
				{
					ALCore.instance.player.addChatComponentMessage(new ChatComponentText("Error caught when compiling the lua file: " + this.file + ".lua, check the console for more information."));
				}
				
				e.printStackTrace();
			}
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
	}

	public void onChanged(File file) throws IOException {
		newTime = file.lastModified();

		if (oldTime != newTime) {
			ALCore.instance.player.addChatComponentMessage(new ChatComponentText("Reloaded the lua file: " + this.file + ".lua"));
			
			try {
				readFile(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void parse(String input) {
		try {
			cs.eval(bindings);
		} catch (ScriptException e1) {
			e1.printStackTrace();
		} catch (LuaError e) {
			e.printStackTrace();
		}
	}

	public void reparse(String input) {
		try {
			cs.eval(bindings);
		} catch (ScriptException e1) {
			e1.printStackTrace();
			
			if(ALCore.instance.player != null)
			{
				ALCore.instance.player.addChatComponentMessage(new ChatComponentText("Error caught when using the lua file: " + file + ".lua, check the console for more information."));
			}
		} catch (LuaError e) {
			e.printStackTrace();
			
			if(ALCore.instance.player != null)
			{
				ALCore.instance.player.addChatComponentMessage(new ChatComponentText("Error caught when using the lua file: " + file + ".lua, check the console for more information."));
			}
		}
	}
}
