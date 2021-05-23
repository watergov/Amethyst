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

package me.lucyy.amethyst.api.module;

import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.amethyst.api.version.SemanticVersion;
import me.lucyy.squirtgun.command.node.CommandNode;
import me.lucyy.squirtgun.platform.SquirtgunPlayer;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * A module.
 * Instances of this class <b>must</b> provide a constructor with a single argument of type AmethystProvider.
 * You should store this provided amethystProvider locally.
 *
 * @author lucy
 * @since 1.0.0
 *
 */
public abstract class AmethystModule implements AutoCloseable {

	/**
	 * Gets the name of this module.
	 *
	 * @since 1.0.0
	 */
	@NotNull
	public abstract String getName();

	/**
	 * Gets the version of this module. The major and minor version should match the core that this plugin is built
	 * against.
	 *
	 * @since 1.0.0
	 */
	@NotNull
	public abstract SemanticVersion getVersion();

	/**
	 * Gets a set of commands that this module exposes. These commands will be exposed as root commands.
	 *
	 * @since 1.0.0
	 */
	@NotNull
	public abstract Set<CommandNode<AmethystUser>> getCommands();

	/**
	 * Parses a placeholder string to a component.
	 *
	 * @param in   the stripped placeholder to parse. For example, the full placeholder %amethyst_core_displayname%
	 *             will be provided as "displayname".
	 * @param user a user for the placeholders to target. This parameter may be null if the placeholder requested is
	 *             not player-specific.
	 * @return a   component containing the parsed placeholder, or null if this placeholder is invalid or a
	 *             player-specific placeholder has been requested when user is null
	 * @since 1.0.0
	 */
	@Nullable
	public Component parsePlaceholder(String in, @Nullable AmethystUser user) {
		return null;
	}

	/**
	 * Called when this module is disposed.
	 */
	@Override
	public void close() {
	}
}
