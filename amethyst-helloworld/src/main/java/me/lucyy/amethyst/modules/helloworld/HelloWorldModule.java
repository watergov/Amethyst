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
import me.lucyy.amethyst.api.module.WaterModule;
import me.lucyy.amethyst.api.version.SemanticVersion;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

/**
 * A very basic example module.
 */
public class HelloWorldModule extends WaterModule {
	private final Set<Subcommand> commands = Set.of(new HelloWorldCommand());

	@Override
	public @NotNull String getName() {
		return "helloworld";
	}

	@Override
	public @NotNull SemanticVersion getVersion() {
		return SemanticVersion.fromString("1.0.0-SNAPSHOT");
	}

	@Override
	public @NotNull Set<Subcommand> getCommands() {
		return commands;
	}
}
