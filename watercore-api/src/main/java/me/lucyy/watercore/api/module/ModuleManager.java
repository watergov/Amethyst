package me.lucyy.watercore.api.module;

import me.lucyy.watercore.api.exception.ModuleInitException;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for loading modules.
 * TODO separate interface and impl
 */
public class ModuleManager {

	private final  Map<Class<? extends WaterModule>, WaterModule> loadedModules = new HashMap<>();

	/**
	 * Gets the instance of a module.
	 *
	 * @param clazz the module class to retrieve
	 * @return the module instance, or null if it's not registered
	 */
	public <T extends WaterModule> @Nullable T getModule(Class<T> clazz) {
		return clazz.cast(loadedModules.get(clazz));
	}

	/**
	 * Gets the instance of a module.
	 *
	 * @param name the name of the module to retrieve
	 * @return the module instance, or null if it's not registered
	 */
	public @Nullable WaterModule getModule(String name) {
		return loadedModules.values().stream()
				.filter(i -> i.getName().equals(name))
				.findFirst()
				.orElse(null);
	}

	/**
	 * Gets all enabled modules.
	 */
	public Collection<WaterModule> getLoadedModules() {
		return loadedModules.values();
	}

	/**
	 * Loads a module.
	 *
	 * @param clazz the class to instantiate
	 * @throws ModuleInitException if an exception is thrown when the module is instantiated or enabled
	 */
	public void loadModule(Class<? extends WaterModule> clazz) throws ModuleInitException {
		try {
			WaterModule module = clazz.getDeclaredConstructor().newInstance();
			module.onEnable();
		} catch (Exception e) {
			throw new ModuleInitException(clazz.getName(), e);
		}
	}
}
