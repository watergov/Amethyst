package me.lucyy.watercore.api.version;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A semantic version.
 *
 * @see <a href="https://semver.org">https://semver.org</a>
 */
public class SemanticVersion implements Comparable<SemanticVersion> {
	private static final Pattern stringPattern = Pattern.compile(
			"^([0-9]+)\\.([0-9]+)\\.([0-9]+)(?:-([0-9A-Za-z-]+(?:\\.[0-9A-Za-z-]+)*))?(?:\\+[0-9A-Za-z-]+)?$");

	private final int major;
	private final int minor;
	private final int patch;
	private @Nullable final String suffix;

	/**
	 * Gets the major version. This should be incremented when you make incompatible API changes.
	 */
	public int getMajor() {
		return major;
	}

	/**
	 * Gets the minor version. This should be incremented when you add functionality in a backwards compatible manner.
	 */
	public int getMinor() {
		return minor;
	}

	/**
	 * Gets the patch version. This should be incremented when you make backwards-compatible bug fixes.
	 */
	public int getPatch() {
		return patch;
	}

	/**
	 * Gets the version suffix. Described as a "pre-release" version in the spec, refers to any text after but not
	 * including a hyphen in theversion.
	 *
	 * @see <a href="https://semver.org#spec-item-9">https://semver.org#spec-item-9</a>
	 */
	public @Nullable String getSuffix() {
		return suffix;
	}

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

	/**
	 * Creates an instance from a string.
	 *
	 * @param in the string to parse
	 * @throws NumberFormatException if the string is improperly formatted
	 */
	public SemanticVersion(String in) {
		this(stringPattern.matcher(in));
	}

	private SemanticVersion(Matcher matcher) {
		this(
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
