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

package me.lucyy.amethyst.api.impl.data;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

/**
 * A data store tied to a YAML file.
 *
 * @author lucy
 */
public class BukkitConfigDataStore extends AbstractBukkitDataStore {
	private final File baseFile;

	/**
	 * @param file the YML file to use as a base
	 */
	public BukkitConfigDataStore(File file) {
		this(YamlConfiguration.loadConfiguration(file), file);
	}

	private BukkitConfigDataStore(YamlConfiguration cfg, File file) {
		super(cfg);
		cfg.options().copyDefaults(true);
		baseFile = file;
	}

	@Override
	protected void save() {
		try {
			((YamlConfiguration) base).save(baseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void reload() {
		try {
			((YamlConfiguration) base).load(baseFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public BukkitSectionDataStore getSection(String name) {
		return new BukkitSectionDataStore(base.getConfigurationSection(name), this);
	}
}
