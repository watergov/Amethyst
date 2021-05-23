/*
 * Copyright © 2021 Lucy Poulton.
 * This file is part of amethyst.
 *
 * amethyst is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * amethyst is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with amethyst.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.lucyy.amethyst.api.impl;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.data.DataStore;
import me.lucyy.amethyst.api.impl.data.BukkitConfigDataStore;
import me.lucyy.amethyst.api.impl.data.UuidCache;
import me.lucyy.amethyst.api.impl.user.BukkitUserFactory;
import me.lucyy.amethyst.api.module.AmethystModule;
import me.lucyy.amethyst.api.module.ModuleManager;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.amethyst.api.version.SemanticVersion;
import me.lucyy.amethyst.core.AmethystPlugin;
import me.lucyy.amethyst.core.AmethystVersion;
import me.lucyy.squirtgun.format.FormatProvider;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 * Implementation for amethystProvider.
 *
 * @author lucy
 */
public class AmethystImpl implements AmethystProvider {

	private final ModuleManager moduleManager;
	private final FormatProvider format;
	private final DataStore config;
	private final DataStore dataStore;
	private final UuidCache uuidCache;
	private final BukkitUserFactory userFactory;

	public AmethystImpl(AmethystPlugin plugin) throws NoSuchFieldException, IllegalAccessException {

		userFactory = new BukkitUserFactory(plugin);

		final Field cmdMapField;
		cmdMapField = Bukkit.getServer().getClass().getDeclaredField("commandMap");
		cmdMapField.setAccessible(true);
		CommandMap cmdMap = (CommandMap) cmdMapField.get(Bukkit.getServer());
		config = new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "config.yml"));
		dataStore = new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "datastore.yml"));
		uuidCache = new UuidCache(new BukkitConfigDataStore(
				new File(plugin.getDataFolder(), "uuidcache.yml")));


		moduleManager = new BukkitModuleManager(cmdMap, plugin, this);
		format = new ConfigBoundFormatProvider(config);
	}

	@Override
	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	@Override
	public @Nullable AmethystUser userFromName(String name) {
		@Nullable final UUID uuid = uuidCache.getUuid(name);
		return uuid == null ? null : userFactory.create(uuid);
	}

	@Override
	public @Nullable AmethystUser userFromUuid(UUID uuid) {
		return userFactory.create(uuid);
	}

	@Override
	public SemanticVersion getVersion() {
		return AmethystVersion.VERSION;
	}

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

	@Nullable
	private Component parsePlaceholder(final String placeholder, @Nullable final AmethystUser user) {
		final int idx = placeholder.indexOf("_");
		if (idx == -1) {
			return null;
		}
		final String moduleName = placeholder.substring(0, idx);
		final AmethystModule module = moduleManager.getModule(moduleName);
		if (module == null) {
			return null;
		}
		return module.parsePlaceholder(placeholder.substring(idx), user);
	}

	@Override
	@Contract(pure = true)
	public Component parsePlaceholders(@NotNull final String input, @Nullable final AmethystUser user) {
		Component out = Component.empty();
		final String[] parts = input.split("(?<!\\\\)%");
		final int modulus = input.startsWith("%") ? 1 : 0;
		for (int i = 0; i < parts.length; i++) {
			final String part = parts[i].replace("\\%", "%");
			if (i % 2 == modulus) {
				Component parsed = parsePlaceholder(part, user);
				if (parsed != null) {
					out = out.append(parsed);
					continue;
				}
			}
			out = out.append(Component.text(part));
		}
		return out;
	}

	public UuidCache getUuidCache() {
		return uuidCache;
	}
}
