package me.lucyy.watercore.api.data;

import java.io.Serializable;
import me.lucyy.watercore.api.data.DataKey;
import me.lucyy.watercore.api.data.DataStore;
import org.jetbrains.annotations.Nullable;

public class SqlData implements DataStore {

	@Override
	public <T extends Serializable> @Nullable T getValue(DataKey<T> key) {
		return null;
	}

	@Override
	public <T extends Serializable> void setValue(DataKey<T> key, T value) {

	}

	@Override
	public <T extends Serializable> void setDefaultValue(DataKey<T> key, T value) {

	}
}
