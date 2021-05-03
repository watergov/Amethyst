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

/**
 * Provides methods to interface with the WaterCore API from a module.
 *
 * @author lucy
 * @since 1.0.0
 */
public interface WaterCoreProvider {
	/**
	 * Gets a user from their cached username, or null if this user is not cached.
	 *
	 * @return a user with the given name
	 */
	@Nullable
	WaterCoreUser userFromName(final String name);

	/**
	 * Gets a user from their UUID, or null if this user is not cached.
	 *
	 * @return a user with the given UUID
	 */
	WaterCoreUser userFromUuid(final UUID uuid);

	/**
	 * Gets the module manager.
	 */
	ModuleManager getModuleManager();

	/**
	 * Gets the core's version.
	 */
	SemanticVersion getVersion();

	/**
	 * Gets the plugin's format provider.
	 */
	FormatProvider getFormatProvider();

	/**
	 * Gets a global data store that is tied to this server's config file.
	 *
	 * @return the server's config file
	 */
	DataStore getConfig();

	/**
	 * Gets a global data store that will be shared across any servers running on the same database. This store should
	 * be used to store persistent data, unrelated to config. Data should not be specific to any particular player -
	 * use {@link WaterCoreUser#getDataStore()} for this purpose.
	 *
	 * @return a global data store
	 */
	DataStore getDataStore();

	/**
	 * Parses a string containing WaterCore (not PlaceholderAPI) placeholders that will get parsed to components.
	 * Any invalid placeholders will be ignored and included as-is.
	 *
	 * @param input the string to process
	 * @param user  a user for the placeholders to target, if not needed for the placeholder, then null
	 * @return a Component, possibly containing extra data, including the parsed placeholders
	 */
	Component parsePlaceholders(String input, @Nullable WaterCoreUser user);
}
