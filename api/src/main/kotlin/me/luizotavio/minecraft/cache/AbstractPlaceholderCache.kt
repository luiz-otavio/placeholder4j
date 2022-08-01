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
package me.luizotavio.minecraft.cache

import me.luizotavio.minecraft.Placeholder
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
abstract class AbstractPlaceholderCache(
    val pattern: Pattern
) {

    abstract fun get(key: String): Placeholder<*>?

    abstract fun register(placeholder: Placeholder<*>)

    // Change to return the matcher instead of alot of placeholders
    abstract fun match(message: String): Matcher?

    abstract fun exists(key: String): Boolean

}
