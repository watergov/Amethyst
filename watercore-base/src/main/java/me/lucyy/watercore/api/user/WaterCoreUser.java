package me.lucyy.watercore.api.user;

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
}
