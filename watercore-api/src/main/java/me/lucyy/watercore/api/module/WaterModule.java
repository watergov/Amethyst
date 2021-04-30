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

package me.lucyy.watercore.api.module;

import me.lucyy.common.command.Subcommand;
import java.util.Set;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A module.
 * Instances of this class <b>must</b> provide a constructor with a single argument of type WaterCoreProvider.
 * You should store this provided WaterCoreProvider locally.
 */
public abstract class WaterModule implements AutoCloseable {

	/**
	 * Gets the name of this module.
	 */
	@NotNull
	public abstract String getName();

	/**
	 * Gets the version of this module. The major and minor version should match the core that this plugin is built
	 * against.
	 */
	@NotNull
	public abstract SemanticVersion getVersion();

	/**
	 * Gets a set of commands that this plugin exposes. These commands will be exposed as root commands.
	 */
	@NotNull
	public abstract Set<Subcommand> getCommands();

	/**
	 * Parses a placeholder string to a component.
	 *
	 * @param in   the stripped placeholder to parse. For example, the full placeholder %watercore_core_displayname%
	 *             will be provided as "displayname".
	 * @param user a user for the placeholders to target. This parameter may be null if the placeholder requested is
	 *             not player-specific.
	 * @return a   component containing the parsed placeholder, or null if this placeholder is invalid or a
	 *             player-specific placeholder has been requested when user is null
	 */
	@Nullable
	public Component parsePlaceholder(String in, @Nullable WaterCoreUser user) {
		return null;
	}

	/**
	 * Called when this module is disposed.
	 */
	@Override
	public void close() {
	}
}
