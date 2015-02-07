package com.arcanumLudum.ALCore.modules.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

public class LibLoader extends URLClassLoader
{
    public LibLoader(ClassLoader classLoader)
    {
        super(new URL[0], classLoader);
    }
    synchronized public void addJarToClasspath(String jarName) throws MalformedURLException, ClassNotFoundException
    {
        File filePath = new File(jarName);
        URI uriPath = filePath.toURI();
        URL urlPath = uriPath.toURL();

        addURL(urlPath);
    }
}