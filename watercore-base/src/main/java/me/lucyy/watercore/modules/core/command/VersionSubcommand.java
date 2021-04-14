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
import me.lucyy.common.format.Platform;
import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.core.WaterCoreVersion;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;

import static me.lucyy.common.format.TextFormatter.formatTitle;
import static me.lucyy.watercore.api.WaterCore.getFormatProvider;

public class VersionSubcommand implements Subcommand {
	@Override
	public String getName() {
		return "watercore";
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
				.append(formatTitle("WaterCore", getFormatProvider()).append(nl));

		Component coreVersion = nl.append(getFormatProvider().formatMain("WaterCore version "))
				.append(getFormatProvider().formatAccent(WaterCoreVersion.VERSION.toString()));

		out = out.append(coreVersion).append(nl);

		for (WaterModule module : WaterCore.getModuleManager().getLoadedModules()) {
			Component moduleVersion = getFormatProvider().formatMain(module.getName() + " ")
					.append(getFormatProvider().formatAccent(module.getVersion().toString()));
			out = out.append(moduleVersion).append(nl);
		}
		out = out.append(nl).append(formatTitle("*", getFormatProvider()));
		Platform.send(sender, out);
		return true;
	}
}
