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
package io.github.luizotavio.placeholder4j.cache;

import io.github.luizotavio.placeholder4j.Placeholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class DefaultPlaceholderCache extends PlaceholderCache {

    private final Map<String, Placeholder<?>> placeholders;

    public DefaultPlaceholderCache(@NotNull Pattern pattern) {
        super(pattern);

        placeholders = new HashMap<>();
    }

    @Nullable
    @Override
    public Placeholder<?> get(@NotNull String key) {
        return placeholders.get(key);
    }

    @Override
    public void register(@NotNull Placeholder<?> placeholder) {
        placeholders.put(placeholder.getName(), placeholder);
    }

    @Override
    public boolean exists(@NotNull String key) {
        return placeholders.containsKey(key);
    }

    @Nullable
    @Override
    public Matcher match(@NotNull String message) {
        if (message.isEmpty()) {
            return null;
        }

        return getPattern().matcher(message);
    }
}
