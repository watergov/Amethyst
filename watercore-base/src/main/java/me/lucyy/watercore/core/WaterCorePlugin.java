package me.lucyy.watercore.core;

import me.lucyy.watercore.api.impl.WaterCoreImpl;
import me.lucyy.watercore.core.mysql.MySql;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class WaterCorePlugin extends JavaPlugin {
	private WaterCoreImpl waterCore;
	public static FileConfiguration config;

	@Override
	public void onEnable() {
		saveDefaultConfig();
		config = getConfig();

		waterCore = new WaterCoreImpl();
	}

	@Override
	public void onDisable() {
		MySql.instance.close();
		// Plugin shutdown logic
	}
}
