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

import io.github.luizotavio.placeholder4j.cache.DefaultPlaceholderCache;
import io.github.luizotavio.placeholder4j.replacer.DefaultPlaceholderReplacer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class PlaceholderAPI {

    private static final Logger LOGGER = Logger.getLogger(PlaceholderAPI.class.getName());

    private final DefaultPlaceholderReplacer replacer;

    public PlaceholderAPI() {
        this(new DefaultPlaceholderReplacer());
    }

    protected PlaceholderAPI(@NotNull DefaultPlaceholderReplacer replacer) {
        this.replacer = replacer;
    }

    public void register(@NotNull Placeholder<?> placeholder) {
        DefaultPlaceholderCache cache = replacer.getCache();

        if (cache != null) {
            cache.register(placeholder);
        }
    }

    public String replace(@NotNull String text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (Exception exception) {
            LOGGER.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }

    public String[] replace(@NotNull String[] text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (Exception exception) {
            LOGGER.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }

    public Collection<String> replace(@NotNull Collection<String> text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (Exception exception) {
            LOGGER.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }
}
