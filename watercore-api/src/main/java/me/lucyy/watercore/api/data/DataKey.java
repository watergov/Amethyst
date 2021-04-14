package me.lucyy.watercore.api.data;

import me.lucyy.watercore.api.module.WaterModule;
import java.io.Serializable;
import java.util.Objects;

/**
 * A key for a value in a {@link DataStore}.
 *
 * @param <T> the type of the value at this key
 */
public class DataKey<T extends Serializable> {
	private final String parent;
	private final String child;
	private final Class<T> clazz;

	String getParent() {
		return parent;
	}

	String getChild() {
		return child;
	}

	/**
	 * Creates a new key.
	 *
	 * @param parent the module this key belongs to
	 * @param child the child string node
	 * @param clazz the class of the expected type
	 */
	public DataKey(WaterModule parent, String child, Class<T> clazz) {
		this.parent = parent.getName();
		this.child = child;
		this.clazz = clazz;
	}

	public Class<T> getClazz() {
		return clazz;
	}

	@Override
	public String toString() {
		return this.parent + "." + this.child;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parent, child);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof DataKey<?>)) {
			return false;
		}
		DataKey<?> otherCasted = (DataKey<?>) other;
		return parent.equals(otherCasted.getParent())
				&& child.equals(otherCasted.getChild());
	}
}
