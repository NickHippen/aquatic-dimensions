package net.infernalrealms.aquatic_dimensions.mobs.types;

import org.bukkit.entity.Slime;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterData;
import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntitySlime;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;

public class NameTagSlime extends EntitySlime implements ADEntity {
	
	public NameTagSlime(EntityTypes<?> entitytypes, World world) {
		super(EntityTypes.SLIME, world);
	}
	
	public NameTagSlime(EntityTypes<?> entitytypes, World world, MonsterData mobData) {
		super(EntityTypes.SLIME, world);
	}
	
	public void init(MonsterData monsterData) {
		Slime bukkitSelf = (Slime) getBukkitEntity();
		bukkitSelf.setSize(-2);
		bukkitSelf.setInvulnerable(true);
		bukkitSelf.setCollidable(false);
	}
	
	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		// TODO Transfer damage to parent
		return false;
	}

}
