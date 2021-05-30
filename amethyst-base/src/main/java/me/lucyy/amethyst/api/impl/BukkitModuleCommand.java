package me.lucyy.amethyst.api.impl;

import me.lucyy.amethyst.api.AmethystProvider;
import me.lucyy.amethyst.api.impl.user.BukkitConsoleUser;
import me.lucyy.amethyst.api.impl.user.BukkitUserFactory;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.squirtgun.command.context.StringContext;
import me.lucyy.squirtgun.command.node.CommandNode;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class BukkitModuleCommand extends Command {

    private final CommandNode<AmethystUser> node;
    private final AmethystProvider provider;
    private final BukkitUserFactory userFactory;

    public BukkitModuleCommand(CommandNode<AmethystUser> node, BukkitUserFactory userFactory, AmethystProvider provider) {
        super(node.getName(), node.getDescription(), "", Collections.emptyList());
        this.node = node;
        this.userFactory = userFactory;
        this.provider = provider;
    }

    private AmethystUser getAmethystUser(CommandSender sender) {
        if (sender instanceof Player) {
            return userFactory.create(((Player) sender).getUniqueId());
        }
        return userFactory.console();
    }

    @Override
    public boolean execute(@NotNull CommandSender commandSender, @NotNull String s, @NotNull String[] args) {

        AmethystUser user = getAmethystUser(commandSender);
        Component out = new StringContext<>(provider.getFormatProvider(), user,
                node, String.join(" ", args))
                .execute();

        if (out != null) {
            user.sendMessage(out);
        }

        return true;
    }

    @NotNull
    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        AmethystUser user = getAmethystUser(sender);

        List<String> ret = new StringContext<>(provider.getFormatProvider(), user,
                node, String.join(" ", args)).tabComplete();

        return ret == null ? Collections.emptyList() : ret;
    }
}
