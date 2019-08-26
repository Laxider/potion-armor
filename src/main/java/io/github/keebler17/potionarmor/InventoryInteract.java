package io.github.keebler17.potionarmor;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.codingforcookies.armorequip.ArmorEquipEvent;

public class InventoryInteract implements Listener {

	PotionArmor plugin;

	public InventoryInteract(PotionArmor plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onInventoryClick(ArmorEquipEvent e) {
		if (e.getPlayer().hasPermission("potionarmor.use")) {
			if (e.getNewArmorPiece().getItemMeta() != null) {
				String key = e.getNewArmorPiece().getItemMeta().getDisplayName();
				if (plugin.armor.containsKey(key)) {
					e.getPlayer().addPotionEffect(plugin.armor.get(key));
				}
			} else {
				String key = e.getOldArmorPiece().getItemMeta().getDisplayName();
				if (plugin.armor.containsKey(key)) {
					e.getPlayer().removePotionEffect(plugin.armor.get(key).getType());
				}
			}
		}
	}

}
