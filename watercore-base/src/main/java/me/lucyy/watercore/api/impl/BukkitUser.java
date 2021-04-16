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

import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.data.DataKey;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.user.WaterCoreUser;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import java.util.UUID;

public class BukkitUser implements WaterCoreUser {
	private final UUID uuid;

	private static final DataKey<String> displayNameFormat =
			new DataKey<>("core", "displayNameFormat", String.class);

	static {
		WaterCore.getConfig().setDefaultValue(displayNameFormat, "%core_username%");
	}

	public BukkitUser(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public Component getDisplayName() {
		return WaterCore.parsePlaceholders(WaterCore.getConfig().getValue(displayNameFormat), this);
	}

	@Override
	public DataStore getDataStore() {
		return null;
	}

	@Override
	public UUID getUuid() {
		return uuid;
	}

	@Override
	public String getUsername() {
		return Bukkit.getOfflinePlayer(uuid).getName();
	}
}
