package io.github.keebler17.potionarmor;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionArmor extends JavaPlugin {

	Map<String, PotionEffect> armor;

	public void loadFromConfig() {
		armor = new HashMap<String, PotionEffect>();

		for (String key : this.getConfig().getKeys(false)) {
			armor.put(this.getConfig().getString(key + ".name"),
					new PotionEffect(PotionEffectType.getByName(this.getConfig().getString(key + ".effect")), 999999,
							this.getConfig().getInt(key + ".power")));
		}
	}

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(new InventoryInteract(this), this);
		this.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);

		this.saveDefaultConfig();

		loadFromConfig();
	}

	public void onDisable() {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (command.getName().equalsIgnoreCase("potionarmor")) {
			if (args.length != 1) {
				sender.sendMessage("&5Potion§dArmor §aby §bKeebler17 §ahelp:\n§5/potionarmor reload§d: Reload configuration");
			} else {
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("potionarmor.reload")) {
						this.reloadConfig();
						sender.sendMessage("§aConfig reloadeded.");
						loadFromConfig();
					}
				}
			}
		}

		return false;
	}

}
