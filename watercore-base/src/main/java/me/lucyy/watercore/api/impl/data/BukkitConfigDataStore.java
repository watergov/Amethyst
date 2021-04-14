package me.lucyy.watercore.api.impl.data;

import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.data.DataKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * A data store tied to a Bukkit ConfigurationSection.
 */
public class BukkitConfigDataStore implements DataStore {
	private final FileConfiguration base;
	private final File baseFile;

	/**
	 * @param file the YML file to use as a base
	 */
	public BukkitConfigDataStore(File file) {
		base = YamlConfiguration.loadConfiguration(file);
		baseFile = file;
	}

	@Override
	public <T extends Serializable> @Nullable T getValue(DataKey<T> key) {
		return base.getObject(key.toString(), key.getClazz());
	}

	@Override
	public <T extends Serializable> void setValue(DataKey<T> key, T value) {
		base.set(key.toString(), value);
		try {
			base.save(baseFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
