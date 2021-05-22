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

package me.lucyy.amethyst.api.user;

import me.lucyy.amethyst.api.data.DataStore;
import net.kyori.adventure.text.Component;
import java.util.UUID;

/**
 * A wrapper around a user, providing additional information.
 *
 * @author lucy
 * @since 1.0.0
 */
public interface WaterCoreUser {

	/**
	 * Gets the user's display name. This will vary depending on the enabled modules and the config, but may contain:
	 * <ul>
	 *     <li>nicknames</li>
	 *     <li>prefixes and suffixes for ranks</li>
	 *     <li>statistics</li>
	 * </ul>
	 *
	 * @since 1.0.0
	 */
	Component getDisplayName();

	/**
	 * Gets the user's global data store. This should be used to store player-specific data.
	 *
	 * @return the user's global data store
	 * @since 1.0.0
	 */
	DataStore getDataStore();

	/**
	 * Gets the user's true username.
	 *
	 * @since 1.0.0
	 */
	String getUsername();

	/**
	 * Gets the user's unique ID depending on the server's authentication method.
	 *
	 * @since 1.0.0
	 */
	UUID getUuid();
}