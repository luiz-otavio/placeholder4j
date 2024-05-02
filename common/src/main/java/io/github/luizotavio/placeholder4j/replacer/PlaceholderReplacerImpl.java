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
package io.github.luizotavio.placeholder4j.replacer;

import io.github.luizotavio.placeholder4j.Placeholder;
import io.github.luizotavio.placeholder4j.cache.PlaceholderCache;
import io.github.luizotavio.placeholder4j.exception.InternalPlaceholderException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
public class PlaceholderReplacerImpl implements PlaceholderReplacer {

    private final PlaceholderCache cache;

    public PlaceholderReplacerImpl(@NotNull PlaceholderCache cache) {
        this.cache = cache;
    }

    @NotNull
    @Override
    public String replace(
        @NotNull String text,
        @NotNull Object... objects
    ) throws InternalPlaceholderException {
        Matcher targets = match(text);

        StringBuilder builder = new StringBuilder(text);
        while (targets.find()) {
            String group = targets.group(1);

            if (group == null) {
                continue;
            }

            Placeholder placeholder = cache.get(group);
            if (placeholder == null) {
                continue;
            }

            Object object = null;
            compatible: for (@NotNull Object targetObject : objects) {
                if (placeholder.isCompatible(targetObject)) {
                    object = targetObject;
                    break compatible;
                }
            }

            if (object == null && objects.length > 0) {
                throw new InternalPlaceholderException("Object not found for placeholder: " + group);
            }

            String replacement = placeholder.resolve(object);

            builder.replace(targets.start(), targets.end(), replacement);
        }

        return builder.toString();
    }

    @NotNull
    @Override
    public Collection<String> replace(
        @NotNull Collection<String> collection,
        @NotNull Object... values
    ) throws InternalPlaceholderException {
        List<String> base = new ArrayList<>(collection);

        for (String text : base) {
            if (text == null || text.isEmpty()) {
                continue;
            }

            base.set(
              base.indexOf(text),
              replace(text, values)
            );
        }

        return base;
    }

    @NotNull
    @Override
    public String[] replace(
        @NotNull String[] collection,
        @NotNull Object... values
    ) throws InternalPlaceholderException {
        for (int i = 0; i < collection.length; i++) {
            String text = collection[i];

            if (text == null || text.isEmpty()) {
                continue;
            }

            collection[i] = replace(text, values);
        }

        return collection;
    }

    @NotNull
    private Matcher match(@NotNull String text) {
        return cache.getPattern()
            .matcher(text);
    }
}
