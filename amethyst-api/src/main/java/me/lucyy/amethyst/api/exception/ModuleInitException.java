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

package me.lucyy.amethyst.api.exception;

/**
 * An exception thrown when a module failes to load and enable.
 *
 * @author lucy
 * @since 1.0.0
 */
public class ModuleInitException extends RuntimeException {
	public ModuleInitException(String moduleName, Throwable cause) {
		super("while initialising module " + moduleName, cause);
	}
}
