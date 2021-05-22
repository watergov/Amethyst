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

import org.bukkit.configuration.ConfigurationSection;

public class BukkitSectionDataStore extends AbstractBukkitDataStore {
	private final AbstractBukkitDataStore parent;

	/**
	 * @param base the ConfigurationSection to use as a base
	 */
	public BukkitSectionDataStore(ConfigurationSection base, AbstractBukkitDataStore parent) {
		super(base);
		this.parent = parent;
	}

	@Override
	protected void save() {
		parent.save();
	}

	@Override
	public void reload() {
		parent.reload();
	}
}
