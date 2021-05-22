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

package me.lucyy.amethyst.modules.core.command;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.module.AmethystModule;
import me.lucyy.amethyst.core.AmethystVersion;
import me.lucyy.common.command.Subcommand;
import me.lucyy.common.format.Platform;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import static me.lucyy.common.format.TextFormatter.formatTitle;

/**
 * Command to show the plugin's and all loaded modules' versions
 *
 * @author lucy
 */
public class VersionSubcommand implements Subcommand {
	
	private final AmethystProvider provider;

	public VersionSubcommand(AmethystProvider provider) {
		this.provider = provider;
	}

	@Override
	public String getName() {
		return "amethyst";
	}

	@Override
	public String getDescription() {
		return "Shows the plugin's and all modules' version.";
	}

	@Override
	public String getUsage() {
		return "";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		Component nl = Component.newline();
		Component out = Component.empty()
				.append(formatTitle("amethyst", provider.getFormatProvider()).append(nl));

		Component coreVersion = nl.append(provider.getFormatProvider().formatMain("amethyst version "))
				.append(provider.getFormatProvider().formatAccent(AmethystVersion.VERSION.toString()));

		out = out.append(coreVersion).append(nl);

		for (AmethystModule module : provider.getModuleManager().getLoadedModules()) {
			Component moduleVersion = provider.getFormatProvider().formatMain(module.getName() + " ")
					.append(provider.getFormatProvider().formatAccent(module.getVersion().toString()));
			out = out.append(moduleVersion).append(nl);
		}
		out = out.append(nl).append(formatTitle("*", provider.getFormatProvider()));
		Platform.send(sender, out);
		return true;
	}
}
