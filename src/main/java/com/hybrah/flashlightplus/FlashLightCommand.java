package com.hybrah.flashlightplus;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class FlashLightCommand implements CommandExecutor {
	private FlashLightPlus plugin;

	public FlashLightCommand(final FlashLightPlus plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(@NotNull final CommandSender sender, @NotNull final Command command, @NotNull final String label, @NotNull final String[] args) {
		if (args.length > 0) return false;

		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Must be a player to execute this command!");
			return true;
		}

		Player player = (Player)sender;
		if (!plugin.getFlashlightEnabled().contains(player.getUniqueId())) {
			player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1000);
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 2147483647, 0));
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("FlashlightOnMsg")));
			plugin.getFlashlightEnabled().add(player.getUniqueId());
			return true;
		}

		player.getWorld().playEffect(player.getLocation(), Effect.SMOKE, 1000);
		player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("FlashlightOffMsg")));
		plugin.getFlashlightEnabled().remove(player.getUniqueId());
		return true;
	}
}
