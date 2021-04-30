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

package me.lucyy.watercore.modules.core;

import me.lucyy.common.command.Subcommand;
import me.lucyy.common.util.UuidUtils;
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import me.lucyy.watercore.core.WaterCoreVersion;
import me.lucyy.watercore.modules.core.command.ReloadSubcommand;
import me.lucyy.watercore.modules.core.command.VersionSubcommand;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Set;

public class CoreModule extends WaterModule {

	private final Set<Subcommand> commands;
	private final WaterCoreProvider provider;

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

	public CoreModule(WaterCoreProvider provider) {
		this.provider = provider;
		commands = Set.of(new VersionSubcommand(provider), new ReloadSubcommand(provider));
	}

	@Override
	public @Nullable Component parsePlaceholder(String in, @Nullable WaterCoreUser user) {
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
				return Component.text(WaterCoreVersion.VERSION.toString());
			default:
				return null;
		}
	}
}
