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

import me.lucyy.watercore.api.data.DataKey;
import me.lucyy.watercore.api.data.DataStore;
import java.util.UUID;

/**
 * Wraps around a data store, caching player usernames and uuids.
 *
 * @author lucy
 */
public class UuidCache {
	private final DataStore dataStore;

	public UuidCache(DataStore dataStore) {
		this.dataStore = dataStore;
	}

	public UUID getUuid(String name) {
		return dataStore.getValue(new DataKey<>("uuidCache", name, UUID.class));
	}

	public void setUuid(String name, UUID id) {
		dataStore.setValue(new DataKey<>("uuidCache", name, UUID.class), id);
	}
}
