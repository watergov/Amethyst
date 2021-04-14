package me.lucyy.watercore.api;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.data.DataStore;
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
		return instance.userFromName(name);
	}

	/**
	 * Gets a user from their UUID, or null if this user is not cached.
	 *
	 * @return a user with the given UUID
	 */
	@Nullable
	public static WaterCoreUser userFromUuid(final UUID uuid) {
		return instance.userFromUuid(uuid);
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

	/**
	 * Gets the plugin's format provider.
	 */
	public static FormatProvider getFormatProvider() {
		return instance.getFormatProvider();
	}


	/**
	 * Gets a global data store that is tied to this server's config file.
	 *
	 * @return the server's config file
	 */
	public static DataStore getConfig() {
		return instance.getConfig();
	}

	/**
	 * Gets a global data store that will be shared across any servers running on the same database. This store should
	 * be used to store persistent data, unrelated to config. Data should not be specific to any particular player -
	 * use {@link WaterCoreUser#getDataStore()} for this purpose.
	 *
	 * @return a global data store
	 */
	public static DataStore getDataStore() {
		return instance.getDataStore();
	}
}
