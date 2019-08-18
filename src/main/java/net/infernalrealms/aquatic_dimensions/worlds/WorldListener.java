package net.infernalrealms.aquatic_dimensions.worlds;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;

public class WorldListener implements Listener {

	@EventHandler(priority=EventPriority.HIGHEST)
	public void worldInit(WorldInitEvent e) {
		System.out.println("World init: " + e.getWorld().getName());
		if (e.getWorld().getName().toLowerCase().contains("instances")) {
			System.out.println("Cancelling");
			e.getWorld().setKeepSpawnInMemory(false);
		}
	}
	
}
