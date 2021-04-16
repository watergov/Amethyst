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

package me.lucyy.watercore.api;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class WaterCore {
	private static WaterCoreProvider instance;

	public static void setProvider(WaterCoreProvider provider) {
		instance = provider;
	}

	/**
	 * Gets a user from their cached username, or null if this user is not cached.
	 *
	 * @return a user with the given name
	 */
	@Nullable
	public static WaterCoreUser userFromName(final String name) {
		return instance.userFromName(name);
	}

	/**
	 * Gets a user from their UUID, or null if this user is not cached.
	 *
	 * @return a user with the given UUID
	 */
	@Nullable
	public static WaterCoreUser userFromUuid(final UUID uuid) {
		return instance.userFromUuid(uuid);
	}

	/**
	 * Gets the module manager.
	 */
	public static ModuleManager getModuleManager() {
		return instance.getModuleManager();
	}

	/**
	 * Gets the core's version.
	 */
	public static SemanticVersion getVersion() {
		return instance.getVersion();
	}

	/**
	 * Gets the plugin's format provider.
	 */
	public static FormatProvider getFormatProvider() {
		return instance.getFormatProvider();
	}


	/**
	 * Gets a global data store that is tied to this server's config file.
	 *
	 * @return the server's config file
	 */
	public static DataStore getConfig() {
		return instance.getConfig();
	}

	/**
	 * Gets a global data store that will be shared across any servers running on the same database. This store should
	 * be used to store persistent data, unrelated to config. Data should not be specific to any particular player -
	 * use {@link WaterCoreUser#getDataStore()} for this purpose.
	 *
	 * @return a global data store
	 */
	public static DataStore getDataStore() {
		return instance.getDataStore();
	}

	/**
	 * Parses a string containing WaterCore (not PlaceholderAPI) placeholders that will get parsed to components.
	 * Any invalid placeholders will be ignored and included as-is.
	 *
	 * @param input the string to process
	 * @param user a user for the placeholders to target, if not needed for the placeholder, then null
	 * @return a Component, possibly containing extra data, including the parsed placeholders
	 */
	public static Component parsePlaceholders(String input, @Nullable WaterCoreUser user) {
		return instance.parsePlaceholders(input, user);
	}
}
