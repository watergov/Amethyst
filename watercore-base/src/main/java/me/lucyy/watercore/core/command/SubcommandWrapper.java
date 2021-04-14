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

package me.lucyy.watercore.core.command;

import me.lucyy.common.command.Subcommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SubcommandWrapper extends Command {
	private final Subcommand subcommand;

	public SubcommandWrapper(Subcommand subcommand) {
		super(subcommand.getName(), subcommand.getDescription(), "", new ArrayList<>());
		this.subcommand = subcommand;
	}

	@Nullable
	@Override
	public String getPermission() {
		return subcommand.getPermission();
	}

	@NotNull
	@Override
	public String getUsage() {
		return subcommand.getUsage();
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
		boolean result = subcommand.execute(sender, sender, args);
		if (!result) {
			sender.sendMessage(getUsage());
		}
		return result;
	}

	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
		return subcommand.tabComplete(args);
	}
}
