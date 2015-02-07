package com.arcanumLudum.ALCore.modules.loader;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.arcanumLudum.ALCore.modules.MainSuper;
import com.arcanumLudum.ALCore.modules.ModuleApi;

import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ListFiles {
	public static ArrayList<String> modules = new ArrayList<String>();
	public static List<File> resultList = new ArrayList<File>();
	public static List<Class> classList = new ArrayList<Class>();
	public static List<MainSuper> moduleList = new ArrayList<MainSuper>();
	
	private static ModuleApi api = new ModuleApi();
	
	public static int moduleCount = 0;
	
	public static List<File> listf(String directoryName) {
        File directory = new File(directoryName);
        ClassLoader t = Thread.currentThread().getContextClassLoader();        
        List<File> resultList = new ArrayList<File>();
        
        // get all the files from a directory
        File[] fList = directory.listFiles();
        resultList.addAll(Arrays.asList(fList));
        
        for (File file : fList) {
            if (file.isFile()) {            	
            	String pack = "alc_modules." + file.getName().substring(0, file.getName().lastIndexOf('.'));
            	
                if(file.getName().contains(".class") && file.getName().contains("alc_")){
                	
                	MainSuper instance;
					try {
						boolean isModuleEnabled = false;
						
						instance = (MainSuper)t.loadClass(pack).newInstance();
					    instance.initLoad();
					    instance.registerObjects();
					    
					    String m = instance.getClass().getName();
	        		    modules.add(m);
					    moduleList.add(instance);
					    classList.add(t.loadClass(pack));
					    
					    moduleCount++;
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
                }else{
                	
                }
                
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        
        return resultList;
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