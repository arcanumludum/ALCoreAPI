package com.arcanumLudum.ALCore.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import cpw.mods.fml.common.registry.EntityRegistry;

public class ALEntityRegistry {
	public static void registerEntity(Entity entity, String entityName, boolean hasEgg, int solidColor, int spotColor, Object modInstance) {
		createEntity(entity.getClass(), entityName, hasEgg, solidColor, spotColor, modInstance);
	}

	public static void createEntity(Class entityClass, String entityName, boolean hasEgg, int solidColour, int spotColour, Object modInstance) {
		int randomId = EntityRegistry.findGlobalUniqueEntityId();

		EntityRegistry.registerGlobalEntityID(entityClass, entityName, randomId);
		EntityRegistry.registerModEntity(entityClass, entityName, randomId, modInstance, 64, 1, true);
		
		if (hasEgg) {
			createEgg(randomId, solidColour, spotColour);
		}
	}

	private static void createEgg(int randomId, int solidColour, int spotColour) {
		EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColour, spotColour));
	}
}