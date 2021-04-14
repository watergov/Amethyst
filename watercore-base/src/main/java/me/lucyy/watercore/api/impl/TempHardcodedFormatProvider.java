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

package me.lucyy.watercore.api.impl;

import me.lucyy.common.command.FormatProvider;
import me.lucyy.common.format.TextFormatter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

/**
 * @deprecated This class should only be used for testing
 */
public class TempHardcodedFormatProvider implements FormatProvider {
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
		return applyFormatter("&f", s, serialiseFormatters(formatters));
	}

	@Override
	public Component formatAccent(@NotNull String s, TextDecoration... formatters) {
		return applyFormatter("{#80f4ff>}%s{#808aff<}", s, serialiseFormatters(formatters));
	}

	@Override
	public Component getPrefix() {
		return null;
	}
}
