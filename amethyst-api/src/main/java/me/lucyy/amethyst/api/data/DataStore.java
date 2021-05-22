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

package me.lucyy.amethyst.api.data;

import org.jetbrains.annotations.Nullable;
import java.io.Serializable;

/**
 * A persistent key-value store.
 *
 * @author lucy
 * @since 1.0.0
 */
public interface DataStore {

	/**
	 * Gets the value of a key.
	 *
	 * @param key the key to retrieve the value of
	 * @return the value of that key, or null if no value has been set
	 * @since 1.0.0
	 */
	@Nullable <T extends Serializable> T getValue(DataKey<T> key);

	/**
	 * Sets the value of a key.
	 *
	 * @param key   the key to set the value of
	 * @param value the value to set, or null to clear it
	 * @since 1.0.0
	 */
	<T extends Serializable> void setValue(DataKey<T> key, T value);

	/**
	 * Sets the value of a key if it isn't set already.
	 *
	 * @param key	the key to set the default for
	 * @param value the default value to set
	 * @since 1.0.0
	 */
	<T extends Serializable> void setDefaultValue(DataKey<T> key, T value);
}
