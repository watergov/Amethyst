package me.lucyy.watercore.core;

import me.lucyy.watercore.api.data.DataStore;
import me.lucyy.watercore.api.user.WaterCoreUser;
import net.kyori.adventure.text.Component;

public class WaterCoreUserImpl implements WaterCoreUser {
	@Override
	public Component getDisplayName() {
		return null;
	}

	@Override
	public DataStore getDataStore() {
		return null;
	}
}
