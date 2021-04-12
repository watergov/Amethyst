package me.lucyy.watercore.api.module;

import me.lucyy.common.command.Subcommand;
import java.util.Set;
import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.user.WaterCoreUser;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.bukkit.event.Listener;

/**
 * A module.
 */
public abstract class WaterModule implements Listener {
	/**
	 * Gets the name of this module.
	 */
	public abstract String getName();

	/**
	 * Gets the version of this module. The major and minor version should match the core that this plugin is built
	 * against.
	 */
	public abstract SemanticVersion getVersion();

	/**
	 * Gets a set of commands that this plugin exposes. These commands will be exposed as root commands.
	 */
	public abstract Set<Subcommand> getCommands();

	/**
	 * Gets a global data store that will be shared across any servers running on the same database. This store should
	 * be used to store persistent data, unrelated to config. Data should not be specific to any particular player -
	 * use {@link WaterCoreUser#getDataStore()} for this purpose.
	 *
	 * @return a global data store
	 */
	protected DataStore getDataStore() {
		return null; // TODO
	}

	/**
	 * Gets a global data store that is tied to this server's config file.
	 *
	 * @return the server's config file
	 */
	protected DataStore getConfig() {
		return null; // TODO
	}

	/**
	 * Called when this module is enabled.
	 */
	public void onEnable() {
	}

	/**
	 * Called when this module is disabled.
	 */
	public void onDisable() {
	}
}
