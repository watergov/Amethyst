package me.lucyy.watercore.api.module;

import me.lucyy.common.command.Subcommand;
import java.util.Set;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * A module.
 */
public abstract class WaterModule implements Listener {

	/**
	 * Gets the name of this module.
	 */
	@NotNull
	public abstract String getName();

	/**
	 * Gets the version of this module. The major and minor version should match the core that this plugin is built
	 * against.
	 */
	@NotNull
	public abstract SemanticVersion getVersion();

	/**
	 * Gets a set of commands that this plugin exposes. These commands will be exposed as root commands.
	 */
	@NotNull
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
