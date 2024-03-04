/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>5.
 */
package me.luizotavio.minecraft;

import me.luizotavio.minecraft.cache.DefaultPlaceholderCache;
import me.luizotavio.minecraft.exception.InternalPlaceholderException;
import me.luizotavio.minecraft.impl.ConsumerPlaceholder;
import me.luizotavio.minecraft.impl.VariablePlaceholder;
import me.luizotavio.minecraft.replacer.DefaultPlaceholderReplacer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class PlaceholderTest {

    private static DefaultPlaceholderReplacer replacer;

    @BeforeAll
    public static void setupAll() {
        replacer = new DefaultPlaceholderReplacer();

        DefaultPlaceholderCache commonPlaceholderCache = replacer.getCache();

        commonPlaceholderCache.register(new VariablePlaceholder("test-unit-case-1"));
        commonPlaceholderCache.register(new VariablePlaceholder("test-unit-case-2"));
        commonPlaceholderCache.register(new ConsumerPlaceholder("test-unit-case-3", () -> "Luiz Otávio"));
    }

    @Test
    @DisplayName("Test unit case 1")
    public void testUnitCase1() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "1",
          replacer.replace("%test-unit-case-1%", "1")
        );
    }

    @Test
    @DisplayName("Test unit case 2")
    public void testUnitCase2() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "Jonh",
          replacer.replace("%test-unit-case-2%", "Jonh")
        );
    }

    @Test
    @DisplayName("Test unit case 3")
    public void testUnitCase3() throws InternalPlaceholderException {
        Assertions.assertEquals(
          "Luiz Otávio",
          replacer.replace("%test-unit-case-3%")
        );
    }
}
