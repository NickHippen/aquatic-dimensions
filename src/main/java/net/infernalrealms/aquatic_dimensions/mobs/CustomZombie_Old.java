package net.infernalrealms.aquatic_dimensions.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

import net.minecraft.server.v1_14_R1.DamageSource;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityZombie;
import net.minecraft.server.v1_14_R1.World;

public class CustomZombie_Old extends EntityZombie implements AquaticDimensionsEntity {

	private List<Entity> nameTagComponents;
	
	public CustomZombie_Old(EntityTypes<?> entitytypes, World world) {
        super(EntityTypes.ZOMBIE, world);
    }
	
	@Override
	public void init(CustomMobData mobData) {
		AquaticDimensionsEntity.super.init(mobData);
		Zombie bukkitSelf = (Zombie) getBukkitEntity();
		bukkitSelf.setBaby(false);
		setupNametag();
	}
	
	private void setupNametag() {
		nameTagComponents = new ArrayList<>();
		Entity as = CustomEntityType.spawnEntitiy(CustomEntityType.NAME_TAG_AS, getBukkitEntity().getLocation());
		nameTagComponents.add(as);
		Entity slime = CustomEntityType.spawnEntitiy(CustomEntityType.NAME_TAG_SLIME, getBukkitEntity().getLocation());
		nameTagComponents.add(slime);
		getBukkitEntity().addPassenger(slime);
		slime.addPassenger(as);
	}
	
	@Override
	public boolean damageEntity(DamageSource damagesource, float f) {
		boolean val = super.damageEntity(damagesource, f);
		if (getHealth() <= 0) {
			nameTagComponents.forEach(Entity::remove);
		}
		return val;
	}
	
	@Override
	public void setOnFire(int i) {
		// Stop fire
	}
	
}
