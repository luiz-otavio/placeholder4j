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

import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
public class PlaceholderCacheImpl extends AbstractPlaceholderCache {

    private final Map<String, Placeholder<?>> placeholders;

    public PlaceholderCacheImpl(@NotNull Pattern pattern) {
        super(pattern);

        placeholders = new Hashtable<>();
    }

    @Nullable
    @Override
    public Placeholder<?> get(@NotNull String key) {
        return placeholders.get(key);
    }

    @Override
    public boolean remove(@NotNull String key) {
        return placeholders.remove(key) != null;
    }

    @Override
    public boolean exists(@NotNull String key) {
        return placeholders.containsKey(key);
    }

    @Override
    public boolean register(@NotNull Placeholder<?> placeholder) {
        return placeholders.put(placeholder.getName(), placeholder) != null;
    }

    @NotNull
    @Override
    public Collection<Placeholder<?>> getPlaceholders() {
        return Collections.unmodifiableCollection(placeholders.values());
    }
}
