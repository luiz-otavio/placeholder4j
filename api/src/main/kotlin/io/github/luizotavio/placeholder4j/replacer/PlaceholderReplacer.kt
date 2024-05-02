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
package io.github.luizotavio.placeholder4j.replacer

import io.github.luizotavio.placeholder4j.exception.InternalPlaceholderException

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
/**
 * Replacer interface to be implemented by the library to
 * replace placeholders with given text and optional values.
 * The values from the methods are nullable, so you can pass null as a value,
 * and it will be replaced with an empty string.
 * Otherwise, if you are using a placeholder based on a passed parameter,
 * you need to pass the parameter as a value.
 */
interface PlaceholderReplacer {

    /**
     * Replace the given text with the values passed.
     * @param text the text to replace
     * @param values the values to replace
     * @return the replaced text
     * @throws InternalPlaceholderException if there aren't compatible types between the placeholder and the value
     */
    @Throws(InternalPlaceholderException::class)
    fun replace(text: String, vararg values: Any): String

    /**
     * Replace the given string collection with the values passed.
     * @param collection the collection to replace
     * @param values the values to replace
     * @return the replaced collection
     * @throws InternalPlaceholderException if there aren't compatible types between the placeholder and the value
     */
    @Throws(InternalPlaceholderException::class)
    fun replace(collection: Collection<String>, vararg values: Any): Collection<String>

    /**
     * Replace the given string array with the values passed.
     * @param collection the array to replace
     * @param values the values to replace
     * @return the replaced array
     * @throws InternalPlaceholderException if there aren't compatible types between the placeholder and the value
     */
    @Throws(InternalPlaceholderException::class)
    fun replace(collection: Array<String>, vararg values: Any): Array<String>

}
