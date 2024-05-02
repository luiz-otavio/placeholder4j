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
package io.github.luizotavio.placeholder4j

import io.github.luizotavio.placeholder4j.replacer.PlaceholderReplacer

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
/**
 * Placeholder interface to be implemented by the user to create custom placeholders.
 * The placeholder is a way to replace a string with a value, such as:
 * ```
 * class MyPlaceholder implements Placeholder<Player> {
 *      @Override
 *      public String getName() {
 *          return "player_name";
 *      }
 *
 *      @Override
 *      public boolean isCompatible(Object value) {
 *          return value instanceof Player;
 *      }
 *
 *      @Override
 *      public String resolve(Player player) {
 *          return player.getName();
 *      }
 * }
 * ```
 * The placeholder above will replace the %player_name% with the player name.
 *
 * @param T the type of the placeholder.
 * @see PlaceholderReplacer;
 */
interface Placeholder<T> {

    /**
     * Returns the name of the placeholder without the % symbol.
     * @return the name of the placeholder
     */
    fun getName(): String

    /**
     * Check if parameter is compatible with this placeholder, such as:
     * ```
     *  class MyPlaceholder implements Placeholder<Placeholder> {
     *    @Override
     *    public boolean isCompatible(Object value) {
     *      if (!(value instaceof Player player)) {
     *          return false;
     *      }
     *
     *      return true;
     *    }
     *
     *    @Override
     *    public String resolve(Player player) {
     *      return player.getName();
     *    }
     *    ...
     * }
     * ```
     * If the parameter is not compatible, the placeholder will not be replaced.
     * @param value the value to check compatibility
     * @return true if the value is compatible with this placeholder
     */
    fun isCompatible(value: Any): Boolean

    /**
     * Resolve the placeholder with the given value.
     * @param consumer the value to resolve the placeholder based on the type of the placeholder.
     * @return the resolved placeholder without the % symbol.
     */
    fun resolve(consumer: T?): String
}
