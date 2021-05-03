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

package me.lucyy.watercore.modules.core.listener;

import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.impl.data.UuidCache;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

// TODO - register this
public class UuidCachingListener implements Listener {
	private final WaterCoreProvider provider;
	private final UuidCache cache;

	public UuidCachingListener(WaterCoreProvider provider, UuidCache cache) {
		this.provider = provider;
		this.cache = cache;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		final UUID uuid = e.getPlayer().getUniqueId();
		final @Nullable UUID lastUuid = cache.getUuid(e.getPlayer().getName());
		if (!uuid.equals(lastUuid)) {
			if (lastUuid != null) {
				Bukkit.getLogger().warning("[WaterCore] Player '" + e.getPlayer().getName() + "' connected with "
						+ "uuid " + uuid + ", but uuid " + lastUuid + " is cached - overwriting. "
						+ "If you're using BungeeCord, check that IP forwarding is set up properly!");
			}
			cache.setUuid(e.getPlayer().getName(), uuid);
		}
	}
}
