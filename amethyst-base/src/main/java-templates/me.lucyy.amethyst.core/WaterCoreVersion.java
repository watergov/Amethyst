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

package me.lucyy.amethyst.core;

import me.lucyy.amethyst.api.version.SemanticVersion;

/**
 * Stores the plugin's version. Be aware that IntelliJ likes to complain about this a lot.
 * It builds fine, don't worry.
 *
 * @author lucy
 */
public class WaterCoreVersion {
	public static final SemanticVersion VERSION = SemanticVersion.fromString("${project.version}");
}
