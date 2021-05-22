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

package me.lucyy.amethyst.modules.core;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.impl.AmethystImpl;
import me.lucyy.amethyst.api.module.AmethystModule;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.amethyst.api.version.SemanticVersion;
import me.lucyy.amethyst.core.AmethystVersion;
import me.lucyy.amethyst.modules.core.command.ReloadSubcommand;
import me.lucyy.amethyst.modules.core.command.VersionSubcommand;
import me.lucyy.amethyst.modules.core.listener.UuidCachingListener;
import me.lucyy.common.command.Subcommand;
import me.lucyy.common.util.UuidUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * Core module providing basic functionality such as version commands and basic placeholders.
 *
 * @author lucy
 */
public class CoreModule extends AmethystModule {

	private final Set<Subcommand> commands;
	private final AmethystProvider provider;

	@Override
	public @NotNull String getName() {
		return "core";
	}

	@Override
	public @NotNull SemanticVersion getVersion() {
		return provider.getVersion();
	}

	@Override
	public @NotNull Set<Subcommand> getCommands() {
		return commands;
	}

	public CoreModule(AmethystProvider provider) {
		this.provider = provider;
		commands = Set.of(new VersionSubcommand(provider), new ReloadSubcommand(provider));
		Listener listener = new UuidCachingListener(((AmethystImpl) provider).getUuidCache());
		provider.getModuleManager().registerListener(this, listener);
	}

	@Override
	public @Nullable Component parsePlaceholder(String in, @Nullable AmethystUser user) {
		if (user == null) {
			return null;
		}
		switch (in) {
			case "username":
				return Component.text(user.getUsername());
			case "uuid":
				return Component.text(UuidUtils.toString(user.getUuid()));
			case "displayname":
				return user.getDisplayName();
			case "version":
				return Component.text(AmethystVersion.VERSION.toString());
			default:
				return null;
		}
	}
}
