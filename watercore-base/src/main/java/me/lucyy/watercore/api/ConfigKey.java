package me.lucyy.watercore.api;

import java.io.Serializable;

interface ConfigKey<T extends Serializable> {
	WaterModule getParent();

	String getChild();
}
