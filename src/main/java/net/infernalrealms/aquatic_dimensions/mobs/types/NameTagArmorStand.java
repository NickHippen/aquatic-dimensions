package net.infernalrealms.aquatic_dimensions.mobs.types;

import org.bukkit.entity.ArmorStand;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterData;
import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityArmorStand;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.World;

public class NameTagArmorStand extends EntityArmorStand implements ADEntity {
	
//	private EntityLiving parent;
	
	public NameTagArmorStand(EntityTypes<?> entitytypes, World world) {
		super(EntityTypes.ARMOR_STAND, world);
	}
	
	public void init(MonsterData monsterData) {
		ArmorStand bukkitSelf = (ArmorStand) getBukkitEntity();
		bukkitSelf.setVisible(false);
		bukkitSelf.setGravity(false);
		bukkitSelf.setInvulnerable(true);
		bukkitSelf.setCanPickupItems(false);
		bukkitSelf.setSmall(true);
		bukkitSelf.setCustomNameVisible(true);
		bukkitSelf.setCollidable(false);
		bukkitSelf.setCustomName(monsterData.getName());
	}
	
	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
//		parent.damageEntity(damagesource, f);
		// TODO Transfer damage to parent
		return false;
	}

}
