package me.lucyy.watercore.api.impl;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import me.lucyy.watercore.core.WaterCoreVersion;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class WaterCoreImpl implements WaterCoreProvider {

	private final ModuleManager moduleManager = new BukkitModuleManager();

	@Override
	public ModuleManager getModuleManager() {
		return moduleManager;
	}

	// TODO
	@Override
	public @Nullable WaterCoreUser userFromName(String name) {
		return null;
	}

	// TODO
	@Override
	public @Nullable WaterCoreUser userFromUuid(UUID uuid) {
		return null;
	}

	@Override
	public SemanticVersion getVersion() {
		return WaterCoreVersion.VERSION;
	}

	// TODO
	@Override
	public FormatProvider getFormatProvider() {
		return null;
	}

	// TODO
	@Override
	public DataStore getConfig() {
		return null;
	}
}
