package net.infernalrealms.aquatic_dimensions.mobs;

import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;

public interface AquaticDimensionsEntity {
	
	public CraftEntity getBukkitEntity();
	
	/**
	 * Initializes the Aquatic Dimensions entity.
	 * Called after all vanilla Minecraft initialization methods are called on the entity
	 */
	public default void init(CustomMobData mobData) {
	}
	
	public default void setupNameTag() {
	}

}
