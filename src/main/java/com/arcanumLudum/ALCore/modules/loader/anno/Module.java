package com.arcanumLudum.ALCore.modules.loader.anno;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Module 
{
	public static Module[] mList = new Module[1000];
	
	private int ID = 0;
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface Module_ {
		String moduleID() default "";
		String moduleAuthor() default "";
		String moduleVersion() default "";
		boolean moduleEnabled() default false;
	}
	
	public void updateInfo()
	{
		if(mList[ID] == null)
		{
			mList[ID] = this;
			
			ID++;
		}
	}
	
	public String getModuleVersion()
	{
		Module ob = new Module();

	    try {
		      Class c = ob.getClass();

		      Method m = c.getMethod("getModuleVersion", String.class, int.class);

		      Module anno = (Module) m.getAnnotation(Module_.class);

		      return ((Module_) anno).moduleVersion();
	    } catch (NoSuchMethodException exc) {
		      return "";
	    }
	}
	
	public String getModuleAuthor()
	{
		Module ob = new Module();

	    try {
		      Class c = ob.getClass();

		      Method m = c.getMethod("getModuleAuthor", String.class, int.class);

		      Module anno = (Module) m.getAnnotation(Module_.class);

		      return ((Module_) anno).moduleAuthor();
	    } catch (NoSuchMethodException exc) {
		      return "";
	    }
	}
	
	public String getModuleID()
	{
		Module ob = new Module();

	    try {
		      Class c = ob.getClass();

		      Method m = c.getMethod("getModuleID", String.class, int.class);

		      Module anno = (Module) m.getAnnotation(Module_.class);

		      return ((Module_) anno).moduleID();
	    } catch (NoSuchMethodException exc) {
		      return "";
	    }
	}
	
	public boolean getModuleEnabled()
	{
		Module ob = new Module();

	    try {
		      Class c = ob.getClass();

		      Method m = c.getMethod("getModuleEnabled", String.class, int.class);

		      Module anno = (Module) m.getAnnotation(Module_.class);

		      return ((Module_) anno).moduleEnabled();
	    } catch (NoSuchMethodException exc) {
		      return false;
	    }
	}
}