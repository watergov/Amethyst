/*
 * Copyright © 2021 lucy.
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

package me.lucyy.watercore.api.impl.user;

import me.lucyy.common.util.UuidUtils;
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.data.DataKey;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.impl.data.BukkitConfigDataStore;
import me.lucyy.watercore.api.user.WaterCoreUser;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import java.util.UUID;

public class BukkitUser implements WaterCoreUser {
	private final UUID uuid;
	private final WaterCoreProvider provider;
	private final DataKey<String> nameFormatKey;

	public BukkitUser(UUID uuid, WaterCoreProvider provider, DataKey<String> nameFormatKey) {
		this.uuid = uuid;
		this.provider = provider;
		this.nameFormatKey = nameFormatKey;
	}

	@Override
	public Component getDisplayName() {
		return provider.parsePlaceholders(provider.getConfig().getValue(nameFormatKey), this);
	}

	@Override
	public DataStore getDataStore() {
		DataStore store = provider.getDataStore();
		if (store instanceof BukkitConfigDataStore) {
			return ((BukkitConfigDataStore) store).getSection("player." + UuidUtils.toString(getUuid()));
		}
		return null; // todo - depends on mysql, see github #12
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
