/*
 * Copyright © 2021 Lucy Poulton.
 * This file is part of watercore.
 *
 * watercore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * watercore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with watercore.  If not, see <https://www.gnu.org/licenses/>.
 */

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
	private final FormatProvider format;
	private final DataStore config;
	private final DataStore dataStore;

	/**
	 * Default constructor
	 */
	public WaterCoreImpl(WaterCorePlugin plugin) throws NoSuchFieldException, IllegalAccessException {
		final Field cmdMapField;
		cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		cmdMapField.setAccessible(true);
		CommandMap cmdMap = (CommandMap) cmdMapField.get(Bukkit.getServer());
		config = new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "config.yml"));
		dataStore = new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "datastore.yml"));


		moduleManager = new BukkitModuleManager(cmdMap, plugin);
		format = new ConfigBoundFormatProvider(config);
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

	@Override
	public DataStore getConfig() {
		return config;
	}

	@Override
	public DataStore getDataStore() {
		return dataStore;
	}
}
