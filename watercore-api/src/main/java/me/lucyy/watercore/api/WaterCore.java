package me.lucyy.watercore.api;

import me.lucyy.watercore.api.module.ModuleManager;

public abstract class WaterCore {
	private static WaterCore instance;

	protected abstract ModuleManager getModuleManagerInstance();

	public static ModuleManager getModuleManager() {
		return instance.getModuleManagerInstance();
	}
}
