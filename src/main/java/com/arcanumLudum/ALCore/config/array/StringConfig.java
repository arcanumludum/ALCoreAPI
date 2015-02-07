package com.arcanumLudum.ALCore.config.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import scala.Console;

import com.arcanumLudum.ALCore.ALCore;

import net.minecraft.client.Minecraft;

public class StringConfig extends ConfigHandler
{		
	
	public static ConfigHandler inst = new ConfigHandler();

	private Collection list;
	
	private StringConfig(){}
	
	/**
	 * Set all the values
	 * @param name
	 * @param path
	 * @param list
	 */
	public StringConfig(String name, String path, Collection list)
	{
		this.name = name;
		this.list = list;
		this.path = path;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + path);
				
		this.createFile();
		this.read();
	}
	
	public StringConfig(String name, String subfolder, String path, Collection list)
	{
		this.name = name;
		this.list = list;
		this.path = path;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + subfolder + "/" + path);
				
		this.createFile();
		this.read();
	}
	
	
	@Override
	public void modify(String fileName, String subfolder, Object o)
	{
		this.path = fileName;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + subfolder + "/" + path);
		create(file, (Collection)o);
	}
	
	@Override
	public void modify(String fileName, Object o)
	{
		this.path = fileName;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + path);
		create(file, (Collection)o);
	}
	
	
	protected void create(File file, Collection list){
		try
		{
			FileWriter filewriter = new FileWriter(file);
			BufferedWriter buffered = new BufferedWriter(filewriter);
			Console.out().println("Writting...");
			for(String s : (CopyOnWriteArrayList<String>)list){
				removeDuplicates(list);
				buffered.write(s+"\r\n");
			}
			buffered.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void read(){
		try
		{
			FileInputStream inputstream = new FileInputStream(file.getAbsolutePath());
			DataInputStream datastream = new DataInputStream(inputstream);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(datastream));
			
			String s;
			Console.out().println("Reading...");

			while((s = bufferedreader.readLine()) != null){
				list.add(Integer.parseInt(s.toLowerCase().trim()));
			}
			
			bufferedreader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}