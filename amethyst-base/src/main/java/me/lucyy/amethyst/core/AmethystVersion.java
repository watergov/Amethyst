/*
 * Copyright © 2021 Lucy Poulton.
 * This file is part of amethyst.
 *
 * amethyst is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * amethyst is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with amethyst.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.lucyy.amethyst.core;

import me.lucyy.amethyst.api.version.SemanticVersion;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

/**
 * Stores the plugin's version. Be aware that IntelliJ likes to complain about this a lot.
 * It builds fine, don't worry.
 *
 * @author lucy
 */
public class AmethystVersion {
	public static final SemanticVersion VERSION;

	static {
		InputStream file = AmethystVersion.class.getClassLoader().getResourceAsStream("amethyst-version.txt");
		Objects.requireNonNull(file);
		VERSION = SemanticVersion.fromString(new Scanner(file)
				.useDelimiter("\\A")
				.next());
	}
}
