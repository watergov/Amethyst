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
 * Implementation for WaterCore static class.
 *
 * @see WaterCore
 */
public interface WaterCoreProvider {

	/**
	 * @see WaterCore#getModuleManager()
	 */
	ModuleManager getModuleManager();

	/**
	 * @see WaterCore#userFromName(String)
	 */
	@Nullable WaterCoreUser userFromName(String name);

	/**
	 * @see WaterCore#userFromUuid(UUID)
	 */
	@Nullable WaterCoreUser userFromUuid(UUID uuid);

	/**
	 * @see WaterCore#getVersion()
	 */
	SemanticVersion getVersion();

	/**
	 * @see WaterCore#getFormatProvider()
	 */
	FormatProvider getFormatProvider();

	/**
	 * @see WaterCore#getConfig()
	 */
	DataStore getConfig();

	/**
	 * @see WaterCore#getDataStore()
	 */
	DataStore getDataStore();

	/**
	 * @see WaterCore#parsePlaceholders(String, WaterCoreUser)
	 */
	Component parsePlaceholders(String input, WaterCoreUser user);
}
