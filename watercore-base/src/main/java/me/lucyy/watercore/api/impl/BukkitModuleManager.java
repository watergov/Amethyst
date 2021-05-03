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
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.exception.ModuleInitException;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.core.WaterCorePlugin;
import me.lucyy.watercore.core.command.SubcommandWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class BukkitModuleManager implements ModuleManager {

	private final Map<Class<? extends WaterModule>, WaterModule> loadedModules = new HashMap<>();
	private final Map<WaterModule, List<Listener>> listeners = new HashMap<>();
	private final CommandMap commandMap;
	private final WaterCorePlugin plugin;
	private final WaterCoreProvider provider;

	public BukkitModuleManager(@NotNull CommandMap commandMap, WaterCorePlugin plugin, WaterCoreProvider provider) {
		this.commandMap = commandMap;
		this.plugin = plugin;
		this.provider = provider;
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
			WaterModule module = clazz.getConstructor(WaterCoreProvider.class).newInstance(provider);
			for (Subcommand subcmd : module.getCommands()) {
				commandMap.register("watercore." + module.getName(), new SubcommandWrapper(subcmd));
			}
			loadedModules.put(clazz, module);
			plugin.getLogger().info("Loaded module '" + module.getName() + "'.");
		} catch (Exception e) {
			throw new ModuleInitException(clazz.getName(), e);
		}
	}

	@Override
	public void loadModule(@NotNull final File file) {
		if (!file.exists()) {
			return;
		}

		final URL jar;
		try {
			jar = file.toURI().toURL();
		} catch (MalformedURLException e) {
			// this should really never happen
			e.printStackTrace();
			return;
		}

		final URLClassLoader loader = new URLClassLoader(new URL[]{jar}, WaterModule.class.getClassLoader());

		try (final JarInputStream stream = new JarInputStream(jar.openStream())) {
			JarEntry entry;
			while ((entry = stream.getNextJarEntry()) != null) {
				final String name = entry.getName();
				if (name.isEmpty() || !name.endsWith(".class")) {
					continue;
				}

				final String className = name.substring(0, name.lastIndexOf('.')).replace('/', '.');
				try {
					final Class<?> loaded = loader.loadClass(className);
					if (WaterModule.class.isAssignableFrom(loaded)) {
						loadModule(loaded.asSubclass(WaterModule.class));
					}
				} catch (final NoClassDefFoundError ignored) {
					plugin.getLogger().warning("Failed to load class '" + className + "'");
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			plugin.getLogger().severe("Failed to load the module '" + file.getName() + "':");
			e.printStackTrace();
		}
	}

	@Override
	public void registerListener(WaterModule module, Listener listener) {
		if (!listeners.containsKey(module)) {
			listeners.put(module, List.of(listener));
		} else {
			listeners.get(module).add(listener);
		}
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

	@Override
	public void unloadModule(WaterModule module) {
		module.close();
		List<Listener> moduleListeners = listeners.get(module);
		if (moduleListeners != null) {
			for (Listener list : moduleListeners) {
				HandlerList.unregisterAll(list);
			}
			listeners.remove(module);
		}
		loadedModules.remove(module.getClass());
	}


	// fixme - potential concurrent access exception here
	@Override
	public void reloadModules() {
		for (WaterModule module : getLoadedModules()) {
			unloadModule(module);
		}
	}
}
