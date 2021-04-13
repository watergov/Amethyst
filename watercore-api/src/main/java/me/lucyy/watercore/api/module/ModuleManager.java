package me.lucyy.watercore.api.module;

import me.lucyy.watercore.api.exception.ModuleInitException;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
}
