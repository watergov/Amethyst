package me.lucyy.watercore.api;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.module.ModuleManager;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

/**
 * Implementation for WaterCore static class.
 *
 * @see WaterCore
 */
public interface WaterCoreProvider {

	/**
	 * @see WaterCore#getModuleManager()
	 */
	ModuleManager getModuleManager();

	/**
	 * @see WaterCore#userFromName(String)
	 */
	@Nullable WaterCoreUser userFromName(String name);

	/**
	 * @see WaterCore#userFromUuid(UUID)
	 */
	@Nullable WaterCoreUser userFromUuid(UUID uuid);

	/**
	 * @see WaterCore#getVersion()
	 */
	SemanticVersion getVersion();

	/**
	 * @see WaterCore#getFormatProvider()
	 */
	FormatProvider getFormatProvider();

	/**
	 * @see WaterCore#getConfig()
	 */
	DataStore getConfig();
}
