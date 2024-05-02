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
package io.github.luizotavio.placeholder4j;

import io.github.luizotavio.placeholder4j.impl.FunctionPlaceholder;
import io.github.luizotavio.placeholder4j.impl.VariablePlaceholder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
public class PlaceholderTest {

    private static PlaceholderAPI placeholderAPI;

    @BeforeAll
    public static void setupAll() {
        placeholderAPI = new PlaceholderAPI();

        placeholderAPI.register(new VariablePlaceholder("test-unit-case-1", "1"));
        placeholderAPI.register(new VariablePlaceholder("test-unit-case-2", "2"));
        placeholderAPI.register(new FunctionPlaceholder<>("test-unit-case-3", String.class, (value) -> value));
    }

    @Test
    public void checkForTestUnitCase1() {
        assertEquals(
          "1",
          placeholderAPI.replace("%test-unit-case-1%")
        );
    }

    @Test
    public void checkForTestUnitCase2() {
        assertEquals(
          "2",
          placeholderAPI.replace("%test-unit-case-2%")
        );
    }

    @Test
    public void checkForTestUnitCase3() {
        assertEquals(
          "Luiz Otávio",
          placeholderAPI.replace("%test-unit-case-3%", "Luiz Otávio")
        );
    }

    @Test
    public void checkForPlaceholderRemove() {
        assertTrue(
          placeholderAPI.remove("test-unit-case-1")
        );
    }
}
