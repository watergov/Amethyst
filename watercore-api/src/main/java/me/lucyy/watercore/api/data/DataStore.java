package me.lucyy.watercore.api.data;

import me.lucyy.watercore.api.module.WaterModule;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.Objects;

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

		@Override
		public int hashCode() {
			return Objects.hash(parent.getName(), child);
		}

		@Override
		public boolean equals(Object other) {
			if (!(other instanceof Key<?>)) {
				return false;
			}
			Key<?> otherCasted = (Key<?>) other;
			return parent.getName().equals(otherCasted.getParent().getName())
					&& child.equals(otherCasted.getChild());
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
