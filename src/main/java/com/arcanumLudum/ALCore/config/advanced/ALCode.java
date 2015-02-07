package com.arcanumLudum.ALCore.config.advanced;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ALCode 
{
	private BufferedReader fileReader = null;
	private ScriptEngine js = null;
	
	public ConfigHelper helper;
	public ConfigHandler handler;	
	public Bindings bindings = null;
	
	public ALCode(BufferedReader reader)
	{
		this.fileReader = reader;
		
		helper = new ConfigHelper();
		handler = new ConfigHandler();
		
		js = new ScriptEngineManager(null).getEngineByName("javascript");		
	    bindings = js.getBindings(ScriptContext.ENGINE_SCOPE);
	    
	    helper.bindClassToEngine(bindings, System.out, "stdOut");
	    helper.bindClassToEngine(bindings, handler, "cHandler");
	    helper.bindClassToEngine(bindings, new Date(), "date");
	    helper.bindClassToEngine(bindings, new SimpleDateFormat(), "date");
	}
	
	/*
	 * Reads and saves the content of the config file.
	 */	
	public void readFile(File fileName) throws IOException
	{
		String content = "";
		
		try
		{
			String s;
			
			while((s = fileReader.readLine()) != null){
				content += s;
			}
			
			fileReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			parseLine(content);
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
		} 
	}
	
	public void parseLine(String input)
	{		
		try {
			js.eval(input);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}	
}
