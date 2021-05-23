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
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.squirtgun.command.context.CommandContext;
import me.lucyy.squirtgun.command.node.CommandNode;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

/**
 * Command to reload the plugin.
 *
 * @author lucy
 */
public class ReloadSubcommand implements CommandNode<AmethystUser> {

	private final AmethystProvider provider;

	public ReloadSubcommand(AmethystProvider provider) {
		this.provider = provider;
	}

	@Override
	public @NotNull String getName() {
		return "reload";
	}

	@Override
	public String getDescription() {
		return "Reloads the plugin";
	}

	@Override
	public String getPermission() {
		return "amethyst.command.reload";
	}

	@Override
	public Component execute(CommandContext<AmethystUser> context) {
		context.getTarget().sendMessage(
				Component.text("[Amethyst] Reloading... Note this command is a work-in-progress. Not everything is reloaded."));

		if (provider.getConfig() instanceof BukkitConfigDataStore) {
			((BukkitConfigDataStore) provider.getConfig()).reload();
		}
		provider.getModuleManager().reloadModules();
		return Component.text("[amethyst] Reload complete.");
	}
}
