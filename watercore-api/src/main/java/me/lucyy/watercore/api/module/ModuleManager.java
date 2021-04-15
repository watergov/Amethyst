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

package me.lucyy.watercore.api.module;

import me.lucyy.watercore.api.exception.ModuleInitException;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.util.Collection;

/**
 * Responsible for loading modules.
 */
public interface ModuleManager {
	/**
	 * Gets the instance of a module.
	 *
	 * @param clazz the module class to retrieve
	 * @return the module instance, or null if it's not registered
	 */
	<T extends WaterModule> @Nullable T getModule(Class<T> clazz);

	/**
	 * Gets the instance of a module.
	 *
	 * @param name the name of the module to retrieve
	 * @return the module instance, or null if it's not registered
	 */
	@Nullable WaterModule getModule(String name);

	/**
	 * Gets all enabled modules.
	 */
	Collection<WaterModule> getLoadedModules();

	/**
	 * Loads a module.
	 *
	 * @param clazz the class to instantiate
	 * @throws ModuleInitException if an exception is thrown when the module is instantiated or enabled
	 */
	void loadModule(Class<? extends WaterModule> clazz) throws ModuleInitException;

	/**
	 * Loads from a file, instantiating any modules it finds.
	 *
	 * @param file the file to read
	 */
	void loadModule(@NotNull File file);

	/**
	 * Registers an event listener for a module.
	 *
	 * @param module the module to associate the listener with
	 * @param listener the listener to register
	 */
	void registerListener(WaterModule module, Listener listener);

	/**
	 * Unloads a module, deregistering its commands and listeners.
	 *
	 * @param module the module to unload
	 */
	void unloadModule(WaterModule module);
}
