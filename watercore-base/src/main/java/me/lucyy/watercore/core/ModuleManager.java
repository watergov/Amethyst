package me.lucyy.watercore.core;

import me.lucyy.watercore.api.WaterModule;
import org.jetbrains.annotations.Nullable;

/**
 * Responsible for loading modules.
 */
public class ModuleManager {
	private static ModuleManager instance;

	public static ModuleManager getInstance() {
		return instance;
	}

	/**
	 * Gets the instance of a module.
	 * @param clazz the module class to retrieve
	 * @return the module instance
	 */
	public <T extends WaterModule> @Nullable T getModule(Class<T> clazz) {
		return null; // TODO
	};
}
