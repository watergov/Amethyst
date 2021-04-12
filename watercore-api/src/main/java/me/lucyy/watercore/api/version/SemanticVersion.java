package me.lucyy.watercore.api.version;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A semantic version.
 *
 * @see <a href="https://semver.org">https://semver.org</a>
 */
public class SemanticVersion implements Comparable<SemanticVersion> {
	public int major;
	public int minor;
	public int patch;

	/**
	 * Described as a "pre-release" version in the spec, refers to any text after but not including a hyphen in the
	 * version. Used to denote that this version is a pre-release.
	 *
	 * @see <a href="https://semver.org#spec-item-9">https://semver.org#spec-item-9</a>
	 */
	public @Nullable String suffix;

	/**
	 * Creates an instance from major, minor and patch specified as integers.
	 */
	public SemanticVersion(int major, int minor, int patch) {
		this(major, minor, patch, null);
	}

	/**
	 * Creates an instance from major, minor and patch specified as integers, and a string suffix.
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
