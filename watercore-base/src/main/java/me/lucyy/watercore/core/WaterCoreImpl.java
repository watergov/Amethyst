package me.lucyy.watercore.core;

import me.lucyy.watercore.api.WaterCoreProvider;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class WaterCoreImpl implements WaterCoreProvider {
	@Override
	public ModuleManager getModuleManager() {
		return null;
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
}
