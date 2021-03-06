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

package me.lucyy.amethyst.modules.core.listener;

import me.lucyy.amethyst.api.impl.data.UuidCache;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Listens for player joins and adds names/uuids to the cache.
 *
 * @author lucy
 */
public class UuidCachingListener implements Listener {
	private final UuidCache cache;

	public UuidCachingListener(UuidCache cache) {
		this.cache = cache;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final UUID uuid = e.getPlayer().getUniqueId();
		final @Nullable UUID lastUuid = cache.getUuid(e.getPlayer().getName());
		if (!uuid.equals(lastUuid)) {
			if (lastUuid != null) {
				Bukkit.getLogger().warning("[amethyst] Player '" + e.getPlayer().getName() + "' connected with "
						+ "uuid " + uuid + ", but uuid " + lastUuid + " is cached - overwriting. "
						+ "If you're using BungeeCord, check that IP forwarding is set up properly!");
			}
			cache.setUuid(e.getPlayer().getName(), uuid);
		}
	}
}
