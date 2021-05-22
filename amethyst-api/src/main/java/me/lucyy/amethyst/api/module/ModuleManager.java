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

package me.lucyy.amethyst.api.module;

import me.lucyy.amethyst.api.exception.ModuleInitException;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Collection;

/**
 * Responsible for loading modules.
 *
 * @author lucy
 * @since 1.0.0
 */
public interface ModuleManager {
	/**
	 * Gets the instance of a module.
	 *
	 * @param clazz the module class to retrieve
	 * @return the module instance, or null if it's not registered
	 * @since 1.0.0
	 */
	<T extends AmethystModule> @Nullable T getModule(Class<T> clazz);

	/**
	 * Gets the instance of a module.
	 *
	 * @param name the name of the module to retrieve
	 * @return the module instance, or null if it's not registered
	 * @since 1.0.0
	 */
	@Nullable AmethystModule getModule(String name);

	/**
	 * Gets all enabled modules.
	 *
	 * @since 1.0.0
	 */
	Collection<AmethystModule> getLoadedModules();

	/**
	 * Loads a module.
	 *
	 * @param clazz the class to instantiate
	 * @throws ModuleInitException if an exception is thrown when the module is instantiated or enabled
	 * @since 1.0.0
	 */
	void loadModule(Class<? extends AmethystModule> clazz) throws ModuleInitException;

	/**
	 * Loads from a file, instantiating any modules it finds.
	 *
	 * @param file the file to read
	 * @since 1.0.0
	 */
	void loadModule(@NotNull File file);

	/**
	 * Registers an event listener for a module.
	 *
	 * @param module the module to associate the listener with
	 * @param listener the listener to register
	 * @since 1.0.0
	 */
	void registerListener(AmethystModule module, Listener listener);

	/**
	 * Unloads a module, deregistering its commands and listeners.
	 *
	 * @param module the module to unload
	 * @since 1.0.0
	 */
	void unloadModule(AmethystModule module);

	/**
	 * Reloads all modules. This will unregister all commands and events, dispose of the module instance
	 * and then create a new one.
	 *
	 * @since 1.0.0
	 */
	void reloadModules();
}
