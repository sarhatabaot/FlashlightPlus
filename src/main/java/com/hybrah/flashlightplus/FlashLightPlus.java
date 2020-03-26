package com.hybrah.flashlightplus;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class FlashLightPlus extends JavaPlugin {
	private List<UUID> flashlightEnabled = new ArrayList<>();

	@Override
	public void onEnable() {
		saveDefaultConfig();
		getCommand("flashlight").setExecutor(new FlashLightCommand(this));
	}

	public List<UUID> getFlashlightEnabled() {
		return flashlightEnabled;
	}
}
