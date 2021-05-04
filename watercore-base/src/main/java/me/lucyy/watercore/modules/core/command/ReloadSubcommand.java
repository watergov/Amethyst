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

package me.lucyy.watercore.modules.core.command;

import me.lucyy.common.command.Subcommand;
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.impl.data.BukkitConfigDataStore;
import org.bukkit.command.CommandSender;

/**
 * Command to reload the plugin.
 *
 * @author lucy
 */
public class ReloadSubcommand implements Subcommand {

	private final WaterCoreProvider provider;

	public ReloadSubcommand(WaterCoreProvider provider) {
		this.provider = provider;
	}

	@Override
	public String getName() {
		return "watercore-reload";
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
		return "watercore.command.reload";
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		sender.sendMessage("[WaterCore] Reloading... Note this command is a work-in-progress. Not everything is reloaded.");
		if (provider.getConfig() instanceof BukkitConfigDataStore) {
			((BukkitConfigDataStore) provider.getConfig()).reload();
		}
		provider.getModuleManager().reloadModules();
		sender.sendMessage("[WaterCore] Reload complete.");
		return true;
	}
}