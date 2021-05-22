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

package me.lucyy.amethyst.api.impl.data;

import me.lucyy.amethyst.api.data.DataStore;
import me.lucyy.amethyst.api.data.DataKey;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * Base class for bukkit configuration-based data stores
 *
 * @author lucy
 */
public abstract class AbstractBukkitDataStore implements DataStore {
	protected final ConfigurationSection base;

	/**
	 * @param base the ConfigurationSection to use as a base
	 */
	protected AbstractBukkitDataStore(ConfigurationSection base) {
		this.base = base;
	}

	protected abstract void save();

	@Override
	public <T extends Serializable> @Nullable T getValue(DataKey<T> key) {
		return base.getObject(key.toString(), key.getClazz());
	}

	@Override
	public <T extends Serializable> void setValue(DataKey<T> key, T value) {
		base.set(key.toString(), value);
		save();
	}

	@Override
	public <T extends Serializable> void setDefaultValue(DataKey<T> key, T value) {
		base.addDefault(key.toString(), value);
		save();
	}

	/**
	 * Reloads the config from the file.
	 */
	public abstract void reload();
}
