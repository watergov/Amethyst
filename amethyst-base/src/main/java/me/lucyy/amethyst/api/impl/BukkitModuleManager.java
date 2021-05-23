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

package me.lucyy.amethyst.api.impl;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.exception.ModuleInitException;
import me.lucyy.amethyst.api.impl.user.BukkitUserFactory;
import me.lucyy.amethyst.api.module.AmethystModule;
import me.lucyy.amethyst.api.module.ModuleManager;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.amethyst.core.AmethystPlugin;
import me.lucyy.squirtgun.command.node.CommandNode;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * Implementation for the module manager.
 *
 * @author lucy
 */
public class BukkitModuleManager implements ModuleManager {

	private final Map<Class<? extends AmethystModule>, AmethystModule> loadedModules = new HashMap<>();
	private final Map<AmethystModule, List<Listener>> listeners = new HashMap<>();
	private final CommandMap commandMap;
	private final AmethystPlugin plugin;
	private final AmethystProvider provider;
	private final BukkitUserFactory userFactory;

	public BukkitModuleManager(@NotNull CommandMap commandMap, AmethystPlugin plugin, AmethystProvider provider) {
		this.commandMap = commandMap;
		this.plugin = plugin;
		this.provider = provider;
		this.userFactory = new BukkitUserFactory(provider);
	}

	@Override
	public <T extends AmethystModule> @Nullable T getModule(Class<T> clazz) {
		return clazz.cast(loadedModules.get(clazz));
	}

	@Override
	public @Nullable AmethystModule getModule(String name) {
		return loadedModules.values().stream()
				.filter(i -> i.getName().equals(name))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Collection<AmethystModule> getLoadedModules() {
		return loadedModules.values();
	}

	@Override
	public void loadModule(Class<? extends AmethystModule> clazz) throws ModuleInitException {
		try {
			AmethystModule module = clazz.getConstructor(AmethystProvider.class).newInstance(provider);
			for (CommandNode<AmethystUser> subcmd : module.getCommands()) {
				commandMap.register("amethyst." + module.getName(),
						new BukkitModuleCommand(subcmd, userFactory, provider));
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

		final URLClassLoader loader = new URLClassLoader(new URL[]{jar}, AmethystModule.class.getClassLoader());

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
					if (AmethystModule.class.isAssignableFrom(loaded)) {
						loadModule(loaded.asSubclass(AmethystModule.class));
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
	public void registerListener(AmethystModule module, Listener listener) {
		if (!listeners.containsKey(module)) {
			listeners.put(module, List.of(listener));
		} else {
			listeners.get(module).add(listener);
		}
		Bukkit.getPluginManager().registerEvents(listener, plugin);
	}

	@Override
	public void unloadModule(AmethystModule module) {
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
		for (AmethystModule module : getLoadedModules()) {
			unloadModule(module);
		}
	}
}
