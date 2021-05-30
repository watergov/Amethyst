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
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.amethyst.core.AmethystVersion;
import me.lucyy.squirtgun.command.context.CommandContext;
import me.lucyy.squirtgun.command.node.CommandNode;
import me.lucyy.squirtgun.format.FormatProvider;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import static me.lucyy.squirtgun.format.TextFormatter.formatTitle;

/**
 * Command to show the plugin's and all loaded modules' versions
 *
 * @author lucy
 */
public class VersionCommandNode implements CommandNode<AmethystUser> {
	
	private final AmethystProvider provider;

	public VersionCommandNode(AmethystProvider provider) {
		this.provider = provider;
	}

	@Override
	public @NotNull String getName() {
		return "version";
	}

	@Override
	public String getDescription() {
		return "Shows the plugin's and all modules' version.";
	}
	

	@Override
	public Component execute(final CommandContext<AmethystUser> context) {
		final Component nl = Component.newline();
		final FormatProvider fmt = context.getFormat();
		
		Component out = Component.empty()
				.append(formatTitle("Amethyst", fmt).append(nl));

		Component coreVersion = nl.append(fmt.formatMain("Amethyst version "))
				.append(fmt.formatAccent(AmethystVersion.VERSION.toString()));

		out = out.append(coreVersion).append(nl);

		for (AmethystModule module : provider.getModuleManager().getLoadedModules()) {
			Component moduleVersion = fmt.formatMain(module.getName() + " ")
					.append(fmt.formatAccent(module.getVersion().toString()));
			out = out.append(moduleVersion).append(nl);
		}
		out = out.append(nl).append(formatTitle("*", fmt));
		return out;
	}
}
