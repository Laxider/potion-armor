package io.github.keebler17.potionarmor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

	PotionArmor plugin;
	
	public PlayerJoin(PotionArmor plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		if (e.getPlayer().getInventory().getHelmet() != null) {
			String key = e.getPlayer().getInventory().getHelmet().getItemMeta().getDisplayName();
			
			if(plugin.armor.containsKey(key)) {
				e.getPlayer().addPotionEffect(plugin.armor.get(key));
			}
		}
	}

}
