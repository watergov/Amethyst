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

package me.lucyy.amethyst.api.version;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A semantic version.
 *
 * @see <a href="https://semver.org">https://semver.org</a>
 * @author lucy
 * @since 1.0.0
 */
public class SemanticVersion implements Comparable<SemanticVersion> {
	// this pattern is not 100% spec-compliant but it works
	private static final Pattern stringPattern = Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)(?>-(.+))?");

	private final int major;
	private final int minor;
	private final int patch;
	private @Nullable final String suffix;

	/**
	 * Gets the major version. This should be incremented when you make incompatible API changes.
	 *
	 * @since 1.0.0
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * Gets the minor version. This should be incremented when you add functionality in a backwards compatible manner.
	 *
	 * @since 1.0.0
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * Gets the patch version. This should be incremented when you make backwards-compatible bug fixes.
	 *
	 * @since 1.0.0
	 */
	public int getPatch() {
		return patch;
	}

	/**
	 * Gets the version suffix. Described as a "pre-release" version in the spec, refers to any text after but not
	 * including a hyphen in theversion.
	 *
	 * @see <a href="https://semver.org#spec-item-9">https://semver.org#spec-item-9</a>
	 * @since 1.0.0
	 */
	public @Nullable String getSuffix() {
		return suffix;
	}

	/**
	 * Creates an instance from major, minor and patch specified as integers.
	 *
	 * @since 1.0.0
	 */
	public SemanticVersion(int major, int minor, int patch) {
		this(major, minor, patch, null);
	}

	/**
	 * Creates an instance from major, minor and patch specified as integers, and a string suffix.
	 *
	 * @since 1.0.0
	 */
	public SemanticVersion(int major, int minor, int patch, @Nullable String suffix) {
		if (major < 0) {
			throw new IllegalArgumentException("Major cannot be below 0");
		}
		this.major = major;
		if (minor < 0) {
			throw new IllegalArgumentException("Minor cannot be below 0");
		}
		this.minor = minor;
		if (patch < 0) {
			throw new IllegalArgumentException("Patch cannot be below 0");
		}
		this.patch = patch;
		this.suffix = suffix;
	}

	/**
	 * Creates an instance from a string.
	 *
	 * @param in the string to parse
	 * @throws NumberFormatException if the string is improperly formatted
	 * @since 1.0.0
	 */
	public static SemanticVersion fromString(String in) {
		Matcher matcher = stringPattern.matcher(in);
		if (!matcher.find()) {
			throw new IllegalArgumentException("Did not match the expected format");
		}
		return new SemanticVersion(
				Integer.parseInt(matcher.group(1)),
				Integer.parseInt(matcher.group(2)),
				Integer.parseInt(matcher.group(3)),
				matcher.group(4)
		);
	}

	@Override
	public int compareTo(@NotNull SemanticVersion o) {
		int result = major - o.major;
		if (result == 0) {
			result = minor - o.minor;
			if (result == 0) {
				result = patch - o.patch;
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return major + "." + minor + "." + patch + (suffix == null ? "" : "-" + suffix);
	}
}
