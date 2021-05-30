package me.lucyy.amethyst.api.impl.user;

import me.lucyy.amethyst.api.data.DataStore;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.squirtgun.platform.Gamemode;
import me.lucyy.squirtgun.platform.SquirtgunPlayer;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.audience.ForwardingAudience;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.UUID;

public class BukkitConsoleUser implements SquirtgunPlayer, AmethystUser, ForwardingAudience.Single {

    private static final UUID ID = new UUID(0 ,0);
    private static final Component NAME_COMPONENT = Component.text("Console");
    private final Audience audience;

	public BukkitConsoleUser(Audience audience) {
		this.audience = audience;
	}

	@Override
    public Component getDisplayName() {
        return NAME_COMPONENT;
    }

    @Override
    public DataStore getDataStore() {
        return null;
    }

    @Override
    public boolean isConsole() {
        return false;
    }

    @Override
    public UUID getUuid() {
        return ID;
    }

    @Override
    public String getUsername() {
        return "Console";
    }

    @Override
    public boolean isOnline() {
        return true;
    }

    @Override
    public boolean hasPermission(String s) {
        return true;
    }

    @Override
    public Gamemode getGamemode() {
        return Gamemode.CREATIVE;
    }

    @Override
    public void setGamemode(Gamemode gamemode) {
        throw new UnsupportedOperationException("Console cannot have a gamemode");
    }

	@Override
	public @NonNull Audience audience() {
		return audience;
	}
}
