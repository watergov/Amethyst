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

package me.lucyy.watercore.core;

import java.sql.SQLException;
import me.lucyy.common.format.Platform;
import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.impl.WaterCoreImpl;
import me.lucyy.watercore.core.mysql.MySqlHandler;
import me.lucyy.watercore.modules.core.CoreModule;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.util.Objects;

public final class WaterCorePlugin extends JavaPlugin {

	public static MySqlHandler mysql = new MySqlHandler();

	@Override
	public void onEnable() {
		try {
			new Platform(this);
			WaterCoreImpl waterCore = new WaterCoreImpl(this);
			WaterCore.setProvider(waterCore);
			waterCore.getModuleManager().loadModule(CoreModule.class); // TODO remove this
			// scan for files in the modules dir
			File modulesDir = new File(getDataFolder(), "modules");
			if (modulesDir.isFile()) {
				getLogger().severe("Modules directory was actually a file!");
				return;
			}
			modulesDir.mkdirs();

			// this should not return null due to the directory check above
			for (File module : Objects.requireNonNull(modulesDir.listFiles())) {
				waterCore.getModuleManager().loadModule(module);
			}

		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			getPluginLoader().disablePlugin(this);
		} catch (ClassNotFoundException e) {
			getPluginLoader().disablePlugin(this);
		}
		saveConfig();
	}

	@Override
	public void onDisable() {
		mysql.close();
	}
}
