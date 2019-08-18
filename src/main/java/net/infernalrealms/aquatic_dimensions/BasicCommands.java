package net.infernalrealms.aquatic_dimensions;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Stopwatch;

import net.infernalrealms.aquatic_dimensions.mobs.CustomEntityType;
import net.infernalrealms.aquatic_dimensions.worlds.WorldUtil;

public class BasicCommands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		if (args.length < 1) {
			return false;
		}
		Player player = (Player) sender;
		switch (args[0]) {
		case "spawnmob":
			player.sendMessage("Spawning monster...");
			CustomEntityType.spawnEntitiy(CustomEntityType.AD_MONSTER, player.getLocation());
			return true;
		case "spawndungeon":
			player.sendMessage("Spawning dungeon instance...");
			Stopwatch sw = Stopwatch.createStarted();
			World world = WorldUtil.loadInstancedWorld(new File("fake_dungeon"));
			sw.stop();
			player.sendMessage("Created new world! (" + sw.elapsed(TimeUnit.MILLISECONDS) + "ms)");
			player.teleport(world.getSpawnLocation());
			return true;
		}
		return false;
	}

}
