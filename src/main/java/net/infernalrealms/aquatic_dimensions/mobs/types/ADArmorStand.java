package net.infernalrealms.aquatic_dimensions.mobs.types;

import java.lang.reflect.Field;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterData;
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
	
	@Override
	public void init(MonsterData mobData) {
		super.init(mobData);
		((LivingEntity) getBukkitEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE));
		((LivingEntity) getBukkitEntity()).getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
	}
	
}
