package me.lucyy.watercore.api.impl;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.WaterCoreProvider;
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

	@Override
	public @Nullable WaterCoreUser userFromName(String name) {
		return null;
	}

	@Override
	public @Nullable WaterCoreUser userFromUuid(UUID uuid) {
		return null;
	}

	@Override
	public SemanticVersion getVersion() {
		return WaterCoreVersion.VERSION;
	}

	@Override
	public FormatProvider getFormatProvider() {
		return null;
	}
}
