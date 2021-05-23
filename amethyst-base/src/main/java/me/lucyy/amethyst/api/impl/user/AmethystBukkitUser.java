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

package me.lucyy.amethyst.api.impl.user;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.data.DataKey;
import me.lucyy.amethyst.api.data.DataStore;
import me.lucyy.amethyst.api.impl.data.BukkitConfigDataStore;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.squirtgun.util.UuidUtils;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;

public class AmethystBukkitUser extends me.lucyy.squirtgun.bukkit.BukkitPlayer implements AmethystUser, ForwardingAudience.Single  {
	private final AmethystProvider provider;
	private final DataKey<String> nameFormatKey;
	private final Audience audience;

	public AmethystBukkitUser(UUID uuid, AmethystProvider provider, DataKey<String> nameFormatKey, Audience audience) {
		super(Bukkit.getOfflinePlayer(uuid));
		this.provider = provider;
		this.nameFormatKey = nameFormatKey;
		this.audience = audience;
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
	public boolean isConsole() {
		return false;
	}

	@Override
	public @NonNull Audience audience() {
		return audience;
	}
}
