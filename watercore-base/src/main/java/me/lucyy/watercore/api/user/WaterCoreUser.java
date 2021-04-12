package me.lucyy.watercore.api.user;

import me.lucyy.watercore.api.data.DataStore;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.Nullable;
import java.util.UUID;

public class WaterCoreUser {

	/**
	 * Gets a user from their cached username, or null if this user is not cached.
	 *
	 * @return a user with the given name
	 */
	@Nullable
	public static WaterCoreUser fromName(final String name) {
		return null; // TODO
	}

	/**
	 * Gets a user from their UUID, or null if this user is not cached.
	 *
	 * @return a user with the given UUID
	 */
	@Nullable
	public static WaterCoreUser fromUuid(final UUID uuid) {
		return null; // TODO
	}

	/**
	 * Gets the user's display name. This will vary depending on the enabled modules, but may contain:
	 * <ul>
	 *     <li>nicknames</li>
	 *     <li>prefixes and suffixes for ranks</li>
	 * </ul>
	 */
	public Component getDisplayName() {
		return null; // TODO
	}

	/**
	 * Gets the user's global data store. This should be used to store player-specific data.
	 *
	 * @return the user's global data store
	 */
	public DataStore getDataStore() {
		return null; // TODO}
	}
}