package net.infernalrealms.aquatic_dimensions;

import org.bukkit.plugin.java.JavaPlugin;

import net.infernalrealms.aquatic_dimensions.mobs.MonsterCache;
import net.infernalrealms.aquatic_dimensions.player.PlayerListener;
import net.infernalrealms.aquatic_dimensions.worlds.WorldListener;

public class AquaticDimensionsPlugin extends JavaPlugin {

	private static AquaticDimensionsPlugin plugin;
	
	@Override
	public void onLoad() {
		plugin = this;
	}

	@Override
	public void onEnable() {
		setupCommands();
		setupListeners();
	}

	@Override
	public void onDisable() {
	}

	private void setupCommands() {
		getCommand("ad").setExecutor(new BasicCommands());
	}
	
	private void setupListeners() {
		getServer().getPluginManager().registerEvents(new WorldListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	public static AquaticDimensionsPlugin getPlugin() {
		return plugin;
	}

}
