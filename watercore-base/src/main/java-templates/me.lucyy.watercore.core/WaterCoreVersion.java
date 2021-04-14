package me.lucyy.watercore.core;

import me.lucyy.watercore.api.version.SemanticVersion;

// intellij complains about this class a lot
public class WaterCoreVersion {
	public static final SemanticVersion VERSION = SemanticVersion.fromString("${project.version}");
}
