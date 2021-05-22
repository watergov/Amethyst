package me.lucyy.amethyst.modules.helloworld;/*
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

import me.lucyy.common.command.Subcommand;
import org.bukkit.command.CommandSender;

public class HelloWorldCommand implements Subcommand {
	@Override
	public String getName() {
		return "helloworld";
	}

	@Override
	public String getDescription() {
		return "Hello world!";
	}

	@Override
	public String getUsage() {
		return "helloworld";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		sender.sendMessage("Hello world!");
		return true;
	}
}
