package net.infernalrealms.aquatic_dimensions.mobs.types;

import java.lang.reflect.Field;

import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;

public class ADArmorStand extends ADMonster {

	public ADArmorStand(EntityTypes<?> entitytypes, World world) {
		super(world);
		try {
			Field entityTypesField = getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getSuperclass().getDeclaredField("f"); // ...
			entityTypesField.setAccessible(true);
			entityTypesField.set(this, EntityTypes.ARMOR_STAND);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
}
