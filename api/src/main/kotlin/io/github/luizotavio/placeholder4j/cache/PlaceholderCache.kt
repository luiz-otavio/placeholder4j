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
package io.github.luizotavio.placeholder4j.cache

import io.github.luizotavio.placeholder4j.Placeholder
import java.util.regex.Pattern

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
/**
 * Placeholder cache interface to be implemented by the library to store placeholders.
 * The cache is used to store placeholders and replace them with values.
 */
interface PlaceholderCache {

    /**
     * Returns the pattern used to match the placeholders.
     * By default, the pattern is %(.+?)%.
     */
    fun getPattern(): Pattern

    fun get(key: String): Placeholder<*>?

    fun register(placeholder: Placeholder<*>): Boolean

    fun remove(key: String): Boolean

    fun exists(key: String): Boolean

    fun getPlaceholders(): Collection<Placeholder<*>>

}
