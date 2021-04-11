package me.lucyy.watercore.api;

import me.lucyy.common.command.Subcommand;
import org.bukkit.event.Listener;

import java.util.Set;

/**
 * A module.
 */
public abstract class WaterModule implements Listener {
	/**
	 * @return the name of this module.
	 */
	public abstract String getName();

	/**
	 * @return the version of this module.
	 */
	public abstract SemanticVersion getVersion();

	/**
	 * @return a set of commands that this plugin exposes. These commands will be exposed as root commands.
	 */
	public abstract Set<Subcommand> getCommands();

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
