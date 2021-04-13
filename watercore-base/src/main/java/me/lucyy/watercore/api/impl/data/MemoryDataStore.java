package me.lucyy.watercore.api.impl.data;

import me.lucyy.watercore.api.data.DataStore;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.util.HashMap;

public class MemoryDataStore implements DataStore {
	private final HashMap<Key<? extends Serializable>, Serializable> storeMap = new HashMap<>();

	@Override
	public <T extends Serializable> @Nullable T getValue(Key<T> key) {
		//noinspection unchecked - application logic ensures type safety
		return (T) storeMap.get(key);
	}

	@Override
	public <T extends Serializable> void setValue(Key<T> key, T value) {
		storeMap.put(key, value);
	}
}
