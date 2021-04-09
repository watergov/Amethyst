package me.lucyy.watercore.api;

import me.lucyy.common.command.Subcommand;
import org.bukkit.event.Listener;

import java.util.Set;

/**
 * A module.
 */
public abstract class WaterModule implements Listener {
	protected WaterModule() {

	}

	public abstract String getName();
	public abstract String getVersion();

	public abstract Set<Subcommand> getSubcommands();

	public void onEnable() { }

	public void onDisable() { }
}
