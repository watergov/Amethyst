package me.lucyy.watercore.api.user;

import me.lucyy.watercore.api.data.DataStore;
import net.kyori.adventure.text.Component;

public interface WaterCoreUser {

	/**
	 * Gets the user's display name. This will vary depending on the enabled modules, but may contain:
	 * <ul>
	 *     <li>nicknames</li>
	 *     <li>prefixes and suffixes for ranks</li>
	 * </ul>
	 */
	Component getDisplayName();

	/**
	 * Gets the user's global data store. This should be used to store player-specific data.
	 *
	 * @return the user's global data store
	 */
	DataStore getDataStore();
}