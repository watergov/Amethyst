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

package me.lucyy.watercore.api.impl.data;

import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.data.DataKey;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * An in-memory data store, primarily for testing.
 *
 * @author lucy
 */
public class MemoryDataStore implements DataStore {

	private final Map<DataKey<? extends Serializable>, Serializable> storeMap = new HashMap<>();

	@Override
	public <T extends Serializable> @Nullable T getValue(DataKey<T> key) {
		//noinspection unchecked - application logic ensures type safety
		return (T) storeMap.get(key);
	}

	@Override
	public <T extends Serializable> void setValue(DataKey<T> key, T value) {
		storeMap.put(key, value);
	}

	@Override
	public <T extends Serializable> void setDefaultValue(DataKey<T> key, T value) {
		if (!storeMap.containsKey(key)) {
			storeMap.put(key, value);
		}
	}
}
