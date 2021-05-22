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

package me.lucyy.amethyst.api.impl;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.common.format.TextFormatter;
import me.lucyy.amethyst.api.data.DataKey;
import me.lucyy.amethyst.api.data.DataStore;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

/**
 * A format provider bound to a DataStore.
 *
 * @author lucy
 */
public class ConfigBoundFormatProvider implements FormatProvider {
	private final DataStore store;
	private final DataKey<String> mainKey;
	private final DataKey<String> accentKey;
	private final DataKey<String> prefixKey;

	/**
	 * Creates a new config-bound format provider.
	 *
	 * @param store the DataStore to use. The keys "core.format.main", "core.format.accent" and "core.format.prefix"
	 *              will be interfaced with.
	 */
	public ConfigBoundFormatProvider(DataStore store) {
		this.store = store;
		mainKey = new DataKey<>("core", "format.main", String.class);
		accentKey = new DataKey<>("core", "format.accent", String.class);
		prefixKey = new DataKey<>("core", "format.prefix", String.class);

		store.setDefaultValue(mainKey, "&f");
		store.setDefaultValue(accentKey, "{#80f4ff>}%s{#808aff<}");
		store.setDefaultValue(prefixKey, "{#80f4ff>}amethyst{#808aff<} &7>>");

	}

	private final Map<TextDecoration, Character> decoStrings = Map.of(
			TextDecoration.OBFUSCATED, 'k',
			TextDecoration.BOLD, 'l',
			TextDecoration.STRIKETHROUGH, 'm',
			TextDecoration.UNDERLINED, 'n',
			TextDecoration.ITALIC, 'o'
	);

	private String serialiseFormatters(TextDecoration... formatters) {
		if (formatters == null) {
			return null;
		}
		StringBuilder out = new StringBuilder();
		for (TextDecoration deco : formatters) {
			out.append(decoStrings.get(deco));
		}
		return out.toString();
	}

	private Component applyFormatter(String formatter, String content, String formatters) {
		return formatter.contains("%s")
				? TextFormatter.format(String.format(formatter, content), formatters, true)
				: TextFormatter.format(formatter + content, formatters, true);
	}

	@Override
	public Component formatMain(@NotNull String s, TextDecoration... formatters) {
		//noinspection ConstantConditions - default value given in constructor
		return applyFormatter(store.getValue(mainKey), s, serialiseFormatters(formatters));
	}

	@Override
	public Component formatAccent(@NotNull String s, TextDecoration... formatters) {
		//noinspection ConstantConditions - default value given in constructor
		return applyFormatter(store.getValue(accentKey), s, serialiseFormatters(formatters));
	}

	@Override
	public Component getPrefix() {
		//noinspection ConstantConditions - default value given in constructor
		return TextFormatter.format(store.getValue(prefixKey));
	}
}
