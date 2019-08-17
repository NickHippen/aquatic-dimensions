package net.infernalrealms.aquatic_dimensions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.infernalrealms.aquatic_dimensions.mobs.CustomEntityType;

public class BasicCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;
		player.sendMessage("Spawning monster...");
//		CustomEntityType.spawnEntitiy(CustomEntityType.CUSTOM_ZOMBIE, player.getLocation());
		CustomEntityType.spawnEntitiy(CustomEntityType.AD_MONSTER, player.getLocation());
//		CustomEntityType.spawnEntitiy(CustomEntityType.NAME_TAG_AS, player.getLocation());
		return true;
	}

}
