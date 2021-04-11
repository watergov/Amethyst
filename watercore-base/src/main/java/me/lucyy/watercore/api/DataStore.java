package me.lucyy.watercore.api;

import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * A persistent key-value store.
 */
public interface DataStore {
	/**
	 * Gets the value of a key.
	 * @param key the key to retrieve the value of
	 * @return the value of that key, or null if no value has been set
	 */
	@Nullable <T extends Serializable> T getValue(ConfigKey<T> key);

	/**
	 * Sets the value of a key.
	 * @param key the key to set the value of
	 * @param value the value to set, or null to clear it
	 */
	<T extends Serializable> void setValue(ConfigKey<T> key, T value);
}
