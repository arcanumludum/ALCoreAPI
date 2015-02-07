package com.arcanumLudum.ALCore.config.array;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import scala.Console;
import com.arcanumLudum.ALCore.ALCore;
import net.minecraft.client.Minecraft;

public class IntegerConfig extends ConfigHandler {

	public volatile static IntegerConfig inst = new IntegerConfig();
	
	private Collection list;

	private IntegerConfig(){}
	
	public IntegerConfig(String name, String path, Collection list){
		this.name = name;
		this.path = path;
		this.list = list;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + path);
		this.createFile();
		this.read();
	}
	
	public IntegerConfig(String name, String path, String subfolder, Collection list){
		this.name = name;
		this.path = path;
		this.list = list;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + subfolder + "/" + path);
		this.createFile();
		this.read();
	}
	
	@Override
	public void modify(String fileName, String subfolder, Object o)
	{
		this.path = fileName;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + subfolder + "/" + path);
		write(file, (Collection)o);
	}
	
	@Override
	public void modify(String fileName, Object o)
	{
		this.path = fileName;
		this.file = new File(ALCore.instance.mc.mcDataDir, "/ALCore/" + path);
		write(file, (Collection)o);
	}
	
	protected void write(File file, Collection list){		
		try
		{
			FileOutputStream filewriter = new FileOutputStream(file);
			BufferedWriter bufferedreader = new BufferedWriter(new OutputStreamWriter(filewriter));
			Console.out().println("Writting...");
			for(int s : (CopyOnWriteArrayList<Integer>)list)
			{
				removeDuplicates(list);
				bufferedreader.write(s + "\r\n");
			}
			bufferedreader.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	protected void read(){
		try
		{
			FileInputStream imputstream = new FileInputStream(file.getAbsolutePath());
			DataInputStream datastream = new DataInputStream(imputstream);
			BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(datastream));
			String s;
			Console.out().println("Reading...");
			while((s = bufferedreader.readLine()) != null){
				Integer i = Integer.parseInt(s);
				list.add(i);
			}
			bufferedreader.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
		
}
