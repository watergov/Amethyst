/*
 * Copyright © 2021 Lucy Poulton.
 * This file is part of watercore.
 *
 * watercore is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * watercore is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with watercore.  If not, see <https://www.gnu.org/licenses/>.
 */

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
