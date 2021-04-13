package me.lucyy.watercore.core;

import me.lucyy.watercore.api.impl.WaterCoreImpl;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaterCorePlugin extends JavaPlugin {
	private WaterCoreImpl waterCore;

	@Override
	public void onEnable() {
		waterCore = new WaterCoreImpl();
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
