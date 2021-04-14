package me.lucyy.watercore.modules.core.command;

import me.lucyy.common.command.Subcommand;
import me.lucyy.common.format.Platform;
import me.lucyy.watercore.api.WaterCore;
import me.lucyy.watercore.api.module.WaterModule;
import me.lucyy.watercore.core.WaterCoreVersion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.CommandSender;

import static me.lucyy.common.format.TextFormatter.formatTitle;
import static me.lucyy.watercore.api.WaterCore.getFormatProvider;

public class VersionSubcommand implements Subcommand {
	@Override
	public String getName() {
		return "watercore";
	}

	@Override
	public String getDescription() {
		return "Shows the plugin's and all modules' version.";
	}

	@Override
	public String getUsage() {
		return "";
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public boolean execute(CommandSender sender, CommandSender target, String[] args) {
		Component nl = Component.newline();
		Component out = Component.empty()
				.append(formatTitle("WaterCore", getFormatProvider()).append(nl));

		Component coreVersion = nl.append(getFormatProvider().formatMain("WaterCore version "))
				.append(getFormatProvider().formatAccent(WaterCoreVersion.VERSION.toString()));

		out = out.append(coreVersion).append(nl);

		for (WaterModule module : WaterCore.getModuleManager().getLoadedModules()) {
			Component moduleVersion = getFormatProvider().formatMain(module.getName() + " ")
					.append(getFormatProvider().formatAccent(module.getVersion().toString()));
			out = out.append(moduleVersion).append(nl);
		}
		out = out.append(nl).append(formatTitle("*", getFormatProvider()));
		Platform.send(sender, out);
		return true;
	}
}
