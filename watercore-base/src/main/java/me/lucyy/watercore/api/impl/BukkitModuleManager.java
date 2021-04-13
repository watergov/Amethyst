package me.lucyy.watercore.api.impl;

import me.lucyy.watercore.api.exception.ModuleInitException;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.module.WaterModule;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BukkitModuleManager implements ModuleManager {

	private final Map<Class<? extends WaterModule>, WaterModule> loadedModules = new HashMap<>();

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
		} catch (Exception e) {
			throw new ModuleInitException(clazz.getName(), e);
		}
	}
}
