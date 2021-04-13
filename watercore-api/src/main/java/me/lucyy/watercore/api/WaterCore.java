package me.lucyy.watercore.api;

import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class WaterCore {
	private static WaterCoreProvider instance;

	public static void setProvider(WaterCoreProvider provider) {
		instance = provider;
	}

	/**
	 * Gets a user from their cached username, or null if this user is not cached.
	 *
	 * @return a user with the given name
	 */
	@Nullable
	public static WaterCoreUser userFromName(final String name) {
		return instance.userFromName(name); // TODO
	}

	/**
	 * Gets a user from their UUID, or null if this user is not cached.
	 *
	 * @return a user with the given UUID
	 */
	@Nullable
	public static WaterCoreUser userFromUuid(final UUID uuid) {
		return instance.userFromUuid(uuid); // TODO
	}

	/**
	 * Gets the module manager.
	 */
	public static ModuleManager getModuleManager() {
		return instance.getModuleManager();
	}

	/**
	 * Gets the core's version.
	 */
	public static SemanticVersion getVersion() {
		return instance.getVersion();
	}
}
