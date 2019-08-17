package net.infernalrealms.aquatic_dimensions.mobs;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_14_R1.CraftWorld;

import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;

import net.minecraft.server.v1_14_R1.BlockPosition;
import net.minecraft.server.v1_14_R1.DataConverterRegistry;
import net.minecraft.server.v1_14_R1.DataConverterTypes;
import net.minecraft.server.v1_14_R1.Entity;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityTypes.b;
import net.minecraft.server.v1_14_R1.EnumCreatureType;
import net.minecraft.server.v1_14_R1.IRegistry;
import net.minecraft.server.v1_14_R1.SharedConstants;
import net.minecraft.server.v1_14_R1.WorldServer;

public enum CustomEntityType {
	
	CUSTOM_ZOMBIE_OLD("customzombie_old", "zombie", CustomZombie_Old::new),
	AD_MONSTER("aquatic_dimensions_monster", "zombie", ADMonster::new), // TODO What are the affects of the baseName being zombie?
	NAME_TAG_AS("name_tag_armor_stand", "armor_stand", NameTagArmorStand::new),
	NAME_TAG_SLIME("name_tag_slime", "slime", NameTagSlime::new);

	private String name;
	private String baseName;
	private b<?> entityCon;
	private EntityTypes<?> typesLoc;
	
	private CustomEntityType(String name, String baseName, b<?> entityCon) {
		this.name = name;
		this.baseName = baseName;
		this.entityCon = entityCon;
		registerEntity();
	}
	
	private void registerEntity() {
		Map<String, Type<?>> types = (Map<String, Type<?>>) DataConverterRegistry.a()
				.getSchema(DataFixUtils.makeKey(SharedConstants.a().getWorldVersion()))
				.findChoiceType(DataConverterTypes.ENTITY).types();
		types.put("minecraft:" + name, types.get("minecraft:" + baseName));
		EntityTypes.a<Entity> a = EntityTypes.a.a(entityCon, EnumCreatureType.MISC);
		typesLoc = IRegistry.a(IRegistry.ENTITY_TYPE, name, a.a(name));
	}
	
	public static org.bukkit.entity.Entity spawnEntitiy(CustomEntityType cet, Location location) {
		return spawnEntitiy(cet, location, null);
	}
	
	public static org.bukkit.entity.Entity spawnEntitiy(CustomEntityType cet, Location location, CustomMobData mobData) {
		WorldServer world = ((CraftWorld) location.getWorld()).getHandle();

		Entity entity = cet.typesLoc.b(world, null, null, null,
				new BlockPosition(location.getX(), location.getY(), location.getZ()), null, false, false);
		if (!world.addEntity(entity)) {
			// LOG THIS
			return null;
		}
		AquaticDimensionsEntity aquaticDimEntity = (AquaticDimensionsEntity) entity;
		aquaticDimEntity.init(mobData);
		return (org.bukkit.entity.Entity) entity.getBukkitEntity();
	}
	
}
