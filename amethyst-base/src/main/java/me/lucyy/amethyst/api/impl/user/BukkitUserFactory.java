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

import me.lucyy.amethyst.api.data.DataKey;
import me.lucyy.amethyst.core.AmethystPlugin;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

import java.util.UUID;

public class BukkitUserFactory {
	private final AmethystPlugin plugin;
	private final DataKey<String> displayNameFormat = new DataKey<>("core", "displayNameFormat", String.class);

	private final BukkitAudiences bukkitAudiences;

	public BukkitUserFactory(AmethystPlugin plugin) {
		this.plugin = plugin;
		bukkitAudiences = BukkitAudiences.create(plugin);
	}

	public AmethystBukkitUser create(UUID uuid) {
		return new AmethystBukkitUser(uuid, plugin.getProvider(), displayNameFormat, bukkitAudiences.player(uuid));
	}

	public BukkitConsoleUser console() {
		return new BukkitConsoleUser(bukkitAudiences.console());
	}
}
