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

package me.lucyy.watercore.api.impl.data;

import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.data.DataKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * A data store tied to a YAML file.
 */
public class BukkitConfigDataStore implements DataStore {
	private final FileConfiguration base;
	private final File baseFile;

	/**
	 * @param file the YML file to use as a base
	 */
	public BukkitConfigDataStore(File file) {
		base = YamlConfiguration.loadConfiguration(file);
		base.options().copyDefaults(true);
		baseFile = file;
	}

	@Override
	public <T extends Serializable> @Nullable T getValue(DataKey<T> key) {
		return base.getObject(key.toString(), key.getClazz());
	}

	@Override
	public <T extends Serializable> void setValue(DataKey<T> key, T value) {
		base.set(key.toString(), value);
		try {
			base.save(baseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public <T extends Serializable> void setDefaultValue(DataKey<T> key, T value) {
		base.addDefault(key.toString(), value);
	}
}
