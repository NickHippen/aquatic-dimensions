package net.infernalrealms.aquatic_dimensions.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void onPlayerHeldItemChange(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		if (player.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		// TODO Actually make this do stuff
		player.getInventory().setHeldItemSlot(0);
	}
	
}
