package me.lucyy.watercore.api.impl.data;

import me.lucyy.watercore.api.data.DataStore;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.Nullable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * A data store tied to a Bukkit ConfigurationSection.
 */
public class BukkitConfigDataStore implements DataStore {
	private final ConfigurationSection base;

	/**
	 * @param base the ConfigurationSection to use as a base
	 */
	public BukkitConfigDataStore(ConfigurationSection base) {
		this.base = base;
	}

	/**
	 * Use reflection to get the Class of a key's type
	 */
	@SuppressWarnings("unchecked")
	private <T extends Serializable> Class<T> getKeyClass(Key<T> key) {
		return ((Class<T>) ((ParameterizedType) key.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0]);
	}

	@Override
	public <T extends Serializable> @Nullable T getValue(Key<T> key) {
		return base.getObject(key.toString(), getKeyClass(key));
	}

	@Override
	public <T extends Serializable> void setValue(Key<T> key, T value) {
		base.set(key.toString(), value);
	}
}
