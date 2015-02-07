/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     cpw - implementation
 */

package com.arcanumLudum.ALCore.modules.loader;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;

public class ItemData {

    private static Map<String, Multiset<String>> modOrdinals = Maps.newHashMap();

    public void setName(String name)
    {   
        ((Multiset<String>) modOrdinals).add(name);
    }
}
