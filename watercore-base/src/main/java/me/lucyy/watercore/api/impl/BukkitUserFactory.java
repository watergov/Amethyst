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

package me.lucyy.watercore.api.impl;

import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.data.DataKey;
import java.util.UUID;

public class BukkitUserFactory {
	private final WaterCoreProvider provider;
	private final DataKey<String> displayNameFormat = new DataKey<>("core", "displayNameFormat", String.class);

	public BukkitUserFactory(WaterCoreProvider provider) {
		this.provider = provider;
	}

	public BukkitUser create(UUID uuid) {
		return new BukkitUser(uuid, provider, displayNameFormat);
	}
}
