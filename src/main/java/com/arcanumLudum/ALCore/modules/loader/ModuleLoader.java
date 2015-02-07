package com.arcanumLudum.ALCore.modules.loader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.minecraft.client.Minecraft;

public class ModuleLoader extends ClassLoader{

    public ModuleLoader(ClassLoader parent) {
        super(parent);
    }

    public Class loadClass(String ModulePath) throws ClassNotFoundException {
        try {
            URL Url1 = new URL(ModulePath);
            URLConnection connection = Url1.openConnection();
            
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return defineClass(ModulePath,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return null;
    }

}