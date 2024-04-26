/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>5.
 */
package io.github.luizotavio.placeholder4j.exception

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
class InternalPlaceholderException : Exception {
    constructor(throwable: Throwable) : super(throwable)

    constructor(message: String) : super(message)
}
