package net.infernalrealms.aquatic_dimensions.mobs.types;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterData;

public interface ADEntity {
	
	/**
	 * Initializes the Aquatic Dimensions entity.
	 * Called after all vanilla Minecraft initialization methods are called on the entity
	 */
	public void init(MonsterData monsterData);
	
}
