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
package me.luizotavio.minecraft.replacer;

import me.luizotavio.minecraft.Placeholder;
import me.luizotavio.minecraft.cache.DefaultPlaceholderCache;
import me.luizotavio.minecraft.exception.InternalPlaceholderException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class DefaultPlaceholderReplacer implements PlaceholderReplacer {

    private final DefaultPlaceholderCache cache;

    public DefaultPlaceholderReplacer() {
        Pattern pattern = Pattern.compile(
          "[%]([^%]+)[%]",
          Pattern.CASE_INSENSITIVE
        );

        this.cache = new DefaultPlaceholderCache(pattern);
    }

    public DefaultPlaceholderCache getCache() {
        return cache;
    }

    @NotNull
    @Override
    public String replace(@NotNull String text, Object... objects) throws InternalPlaceholderException {
        Matcher targets = cache.match(text);

        if (targets == null) {
            return text;
        }

        // Use string builder to avoid multiple string concatenation
        StringBuilder stringBuilder = new StringBuilder(text);

        int index = 0;

        try {
            while (targets.find()) {
                String group = targets.group(1);

                if (group == null) {
                    continue;
                }

                int target = targets.start(),
                    end = targets.end();

                Placeholder placeholder = cache.get(group);

                if (placeholder != null) {
                    Object value = objects.length == 0 || objects.length <= index ? null : objects[index];

                    stringBuilder.replace(target, end, placeholder.resolve(value));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InternalPlaceholderException(
                String.format("Invalid number of values provided: %d", index)
            );
        }

        return stringBuilder.toString();
    }

    @NotNull
    @Override
    public Collection<String> replace(@NotNull Collection<String> collection, Object... values) throws InternalPlaceholderException {
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
    public String[] replace(@NotNull String[] collection, Object... values) throws InternalPlaceholderException {
        for (int i = 0; i < collection.length; i++) {
            String text = collection[i];

            if (text == null || text.isEmpty()) {
                continue;
            }

            collection[i] = replace(text, values);
        }

        return collection;
    }
}
