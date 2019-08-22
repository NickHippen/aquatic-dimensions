package net.infernalrealms.aquatic_dimensions.util;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackUtil {
	
	@Nullable
	public static ItemStack generateFromString(String str) {
		if (str == null) {
			return null;
		}
		Material material = Material.valueOf(str.toUpperCase());
		if (material == null) {
			return null;
		}
		return new ItemStack(material);
	}

}
