package me.lucyy.watercore.core.module;

import me.lucyy.common.command.Subcommand;
import me.lucyy.common.command.VersionSubcommand;
import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.api.version.SemanticVersion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class CoreModule extends WaterModule {

	// TODO avoid any plugin references
	private final Set<Subcommand> commands = Set.of(new VersionSubcommand(WaterCore.getFormatProvider(),
			(JavaPlugin) Bukkit.getPluginManager().getPlugin("WaterCore")));

	@Override
	public @NotNull String getName() {
		return "core";
	}

	@Override
	public @NotNull SemanticVersion getVersion() {
		return WaterCore.getVersion();
	}

	@Override
	public @NotNull Set<Subcommand> getCommands() {
		return commands;
	}

	@Override
	public void onEnable() {

	}
}
