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

package me.lucyy.watercore.api.impl;

import me.lucyy.common.command.Subcommand;
import me.lucyy.watercore.api.exception.ModuleInitException;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.core.command.SubcommandWrapper;
import org.bukkit.command.CommandMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BukkitModuleManager implements ModuleManager {

	private final Map<Class<? extends WaterModule>, WaterModule> loadedModules = new HashMap<>();
	private final CommandMap commandMap;

	public BukkitModuleManager(@NotNull CommandMap commandMap) {
		this.commandMap = commandMap;
	}

	@Override
	public <T extends WaterModule> @Nullable T getModule(Class<T> clazz) {
		return clazz.cast(loadedModules.get(clazz));
	}

	@Override
	public @Nullable WaterModule getModule(String name) {
		return loadedModules.values().stream()
				.filter(i -> i.getName().equals(name))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Collection<WaterModule> getLoadedModules() {
		return loadedModules.values();
	}

	@Override
	public void loadModule(Class<? extends WaterModule> clazz) throws ModuleInitException {
		try {
			WaterModule module = clazz.getDeclaredConstructor().newInstance();
			module.onEnable();
			for (Subcommand subcmd : module.getCommands()) {
				commandMap.register("watercore." + module.getName(), new SubcommandWrapper(subcmd));
			}
			loadedModules.put(clazz, module);
		} catch (Exception e) {
			throw new ModuleInitException(clazz.getName(), e);
		}
	}
}
