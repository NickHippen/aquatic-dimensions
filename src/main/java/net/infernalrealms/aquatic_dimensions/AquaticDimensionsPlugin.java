package net.infernalrealms.aquatic_dimensions;

import org.bukkit.plugin.java.JavaPlugin;

public class AquaticDimensionsPlugin extends JavaPlugin {

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {
		setupCommands();
	}

	@Override
	public void onDisable() {
	}

	private void setupCommands() {
		this.getCommand("spawnmob").setExecutor(new BasicCommands());
	}

}
