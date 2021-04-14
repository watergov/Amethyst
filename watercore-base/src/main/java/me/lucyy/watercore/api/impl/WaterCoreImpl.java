package me.lucyy.watercore.api.impl;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.impl.data.BukkitConfigDataStore;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import me.lucyy.watercore.core.WaterCorePlugin;
import me.lucyy.watercore.core.WaterCoreVersion;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.lang.reflect.Field;
import java.util.UUID;

public class WaterCoreImpl implements WaterCoreProvider {

	private final ModuleManager moduleManager;
	private final FormatProvider format = new TempHardcodedFormatProvider();
	private final WaterCorePlugin plugin;
	private final DataStore config;

	/**
	 * Default constructor
	 */
	public WaterCoreImpl(WaterCorePlugin plugin) throws NoSuchFieldException, IllegalAccessException {
		this.plugin = plugin;
		final Field cmdMapField;
		cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		cmdMapField.setAccessible(true);
		CommandMap cmdMap = (CommandMap) cmdMapField.get(Bukkit.getServer());
		moduleManager = new BukkitModuleManager(cmdMap);

		config = new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "config.yml"));
	}

	@Override
	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	// TODO
	@Override
	public @Nullable WaterCoreUser userFromName(String name) {
		return null;
	}

	// TODO
	@Override
	public @Nullable WaterCoreUser userFromUuid(UUID uuid) {
		return null;
	}

	@Override
	public SemanticVersion getVersion() {
		return WaterCoreVersion.VERSION;
	}

	// TODO
	@Override
	public FormatProvider getFormatProvider() {
		return format;
	}

	// TODO
	@Override
	public DataStore getConfig() {
		return config;
	}

	@Override
	public DataStore getDataStore() {
		return null;
	}
}
