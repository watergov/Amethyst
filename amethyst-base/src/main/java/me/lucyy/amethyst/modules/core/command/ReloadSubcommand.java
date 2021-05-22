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
import me.lucyy.amethyst.api.impl.data.BukkitConfigDataStore;
import me.lucyy.common.command.Subcommand;
import org.bukkit.command.CommandSender;

/**
 * Command to reload the plugin.
 *
 * @author lucy
 */
public class ReloadSubcommand implements Subcommand {

	private final AmethystProvider provider;

	public ReloadSubcommand(AmethystProvider provider) {
		this.provider = provider;
	}

	@Override
	public String getName() {
		return "amethyst-reload";
	}

	@Override
	public String getDescription() {
		return "Reloads the plugin";
	}

	@Override
	public String getUsage() {
		return "null";
	}

	@Override
	public String getPermission() {
		return "amethyst.command.reload";
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		sender.sendMessage("[amethyst] Reloading... Note this command is a work-in-progress. Not everything is reloaded.");
		if (provider.getConfig() instanceof BukkitConfigDataStore) {
			((BukkitConfigDataStore) provider.getConfig()).reload();
		}
		provider.getModuleManager().reloadModules();
		sender.sendMessage("[amethyst] Reload complete.");
		return true;
	}
}
