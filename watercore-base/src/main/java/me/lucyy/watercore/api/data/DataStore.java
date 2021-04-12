package me.lucyy.watercore.api.data;

import me.lucyy.watercore.api.WaterModule;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;

/**
 * A persistent key-value store.
 */
public interface DataStore {
	/**
	 * A key.
	 *
	 * @param <T> the type of the value at this key
	 */
	class Key<T extends Serializable> {
		private final WaterModule parent;
		private final String child;

		WaterModule getParent() {
			return parent;
		}

		String getChild() {
			return child;
		}

		public Key(WaterModule parent, String child) {
			this.parent = parent;
			this.child = child;
		}
	}

	/**
	 * Gets the value of a key.
	 *
	 * @param key the key to retrieve the value of
	 * @return the value of that key, or null if no value has been set
	 */
	@Nullable <T extends Serializable> T getValue(Key<T> key);

	/**
	 * Sets the value of a key.
	 *
	 * @param key   the key to set the value of
	 * @param value the value to set, or null to clear it
	 */
	<T extends Serializable> void setValue(Key<T> key, T value);
}
