package me.lucyy.watercore.core.command;

import me.lucyy.common.command.Subcommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SubcommandWrapper extends Command {
	private final Subcommand subcommand;

	public SubcommandWrapper(Subcommand subcommand) {
		super(subcommand.getName(), subcommand.getDescription(), "", new ArrayList<>());
		this.subcommand = subcommand;
	}

	@Nullable
	@Override
	public String getPermission() {
		return subcommand.getPermission();
	}

	@Override
	public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
		return subcommand.execute(sender, sender, args);
	}

	@NotNull
	@Override
	public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
		return subcommand.tabComplete(args);
	}
}
