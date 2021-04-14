package me.lucyy.watercore.core;

import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.impl.WaterCoreImpl;
import me.lucyy.watercore.core.module.CoreModule;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class WaterCorePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		try {
			WaterCoreImpl waterCore = new WaterCoreImpl();
			WaterCore.setProvider(waterCore);
			waterCore.getModuleManager().loadModule(CoreModule.class); // TODO remove this
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			getPluginLoader().disablePlugin(this);
		}
	}

	@Override
	public void onDisable() {
	}
}
