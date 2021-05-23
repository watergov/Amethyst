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

package me.lucyy.amethyst.core;

import me.lucyy.amethyst.api.impl.AmethystImpl;
import me.lucyy.amethyst.modules.core.CoreModule;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

/**
 * Core plugin. This is the main entrypoint for Amethyst.
 *
 * @author lucy
 */
public final class AmethystPlugin extends JavaPlugin {

	@Override
	public void onEnable() {

		if(getVersion() < 11) {
			getLogger().severe("amethyst requires Java 11 or above! Disabling plugin.");
			getServer().getPluginManager().disablePlugin(this);
		}

		try {
			AmethystImpl amethyst = new AmethystImpl(this);
			amethyst.getModuleManager().loadModule(CoreModule.class);
			// scan for files in the modules dir
			File modulesDir = new File(getDataFolder(), "modules");
			if (modulesDir.isFile()) {
				getLogger().severe("Modules directory was actually a file!");
				getPluginLoader().disablePlugin(this);
				return;
			}
			//noinspection ResultOfMethodCallIgnored
			modulesDir.mkdirs();

			// this should not return null due to the directory check above
			for (File module : Objects.requireNonNull(modulesDir.listFiles())) {
				amethyst.getModuleManager().loadModule(module);
			}

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			getPluginLoader().disablePlugin(this);
		}
		saveConfig();
	}

	@Override
	public void onDisable() {
	}

	private static int getVersion() {
		final String ver = System.getProperty("java.version");
		final int dotIdx = ver.indexOf('.');
		return Integer.parseInt(dotIdx == -1 ? ver : ver.substring(0, dotIdx));
	}
}
