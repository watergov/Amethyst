package me.lucyy.amethyst.api.impl.user;

import me.lucyy.amethyst.api.data.DataStore;
import me.lucyy.amethyst.api.user.AmethystUser;
import me.lucyy.squirtgun.platform.Gamemode;
import me.lucyy.squirtgun.platform.SquirtgunPlayer;
import net.kyori.adventure.text.Component;

import java.util.UUID;

public class BukkitConsoleUser implements SquirtgunPlayer, AmethystUser {

    private static final UUID ID = new UUID(0 ,0);
    private static final Component NAME_COMPONENT = Component.text("Console");

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
}
