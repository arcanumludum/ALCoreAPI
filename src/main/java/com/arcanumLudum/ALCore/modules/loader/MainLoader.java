package com.arcanumLudum.ALCore.modules.loader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

import org.apache.commons.io.FilenameUtils;

import scala.Console;

import com.arcanumLudum.ALCore.modules.MainSuper;
import com.arcanumLudum.ALCore.modules.ModuleApi;

import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class MainLoader {
	
	public static ArrayList<String> modules = new ArrayList<String>();
	public static List<Class> classList = new ArrayList<Class>();
	public static List<MainSuper> moduleList = new ArrayList<MainSuper>();
	
	private static ModuleApi api = new ModuleApi();
	
	public int moduleCount = 0;
	
	@SuppressWarnings("resource")
	public void loadModules(Minecraft mc) throws
		ClassNotFoundException,
	    IllegalAccessException,
	    InstantiationException {

		System.out.println("[ALCore] Looking in the Modules folder for Modules....");
		
	    ClassLoader parentClassLoader = ModuleLoader.class.getClassLoader();
	    ModuleLoader classLoader = new ModuleLoader(parentClassLoader);
	    
		LibLoader loader = new LibLoader(parentClassLoader);
	    
	    try {
	    	String url1 = mc.getMinecraft().mcDataDir.getCanonicalPath() + File.separator + "alc_modules" + File.separator;
	    	
        	File classDir = new File(url1);

        	URL[] url = { classDir.toURI().toURL() };
        	URLClassLoader urlLoader = new URLClassLoader(url);

            File[] fList = classDir.listFiles();
            
        	String filename = "";
        	for (File file : fList) {
        		filename  = file.getName();
        		
        		if(filename.startsWith("alc_") && filename.endsWith(".jar"))
        		{
            		filename = FilenameUtils.removeExtension(file.getName());
        			
        			loader.addJarToClasspath(url1 + file.getName());
        			
            	    MainSuper instance = (MainSuper)Class.forName("alc_modules." + filename, true, loader).newInstance();
            	    instance.initLoad();
            	    instance.registerObjects();
            	    
        		    String m = instance.getClass().getName();
        		    modules.add(m);
        		    moduleList.add(instance);
        		    classList.add(Class.forName("alc_modules." + filename, true, loader));
        		    
        		    Console.out().println("[ALCore] Loaded module: " + m);
        		    moduleCount++;
        		}
        	}

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        } catch (InstantiationException e) {
        	e.printStackTrace();
        }
	}
	
	public static void updatePlayer(PlayerTickEvent event)
	{
        for (MainSuper tmp : moduleList) {
        	api.playerTick(event);
			tmp.playerTick(event);
        }
	}
	
	public static void update(WorldTickEvent event)
	{
		for (MainSuper tmp : moduleList) {
			api.worldTick(event);
			tmp.worldTick(event);
        }
	}
	
	public static void render(RenderTickEvent event)
	{
		for (MainSuper tmp : moduleList) {
			api.renderTick(event);
			tmp.renderTick(event);
        }
	}
}
