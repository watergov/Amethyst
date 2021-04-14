package me.lucyy.watercore.core;

import me.lucyy.common.format.Platform;
import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.impl.WaterCoreImpl;
import me.lucyy.watercore.modules.core.CoreModule;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public final class WaterCorePlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		try {
			new Platform(this);
			WaterCoreImpl waterCore = new WaterCoreImpl(this);
			WaterCore.setProvider(waterCore);
			waterCore.getModuleManager().loadModule(CoreModule.class); // TODO remove this
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
			getPluginLoader().disablePlugin(this);
		} catch (ClassNotFoundException e) {
			getPluginLoader().disablePlugin(this);
		}
		saveConfig();
	}

	@Override
	public void onDisable() {
	}
}
