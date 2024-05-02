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

import io.github.luizotavio.placeholder4j.cache.PlaceholderCache;
import io.github.luizotavio.placeholder4j.cache.PlaceholderCacheImpl;
import io.github.luizotavio.placeholder4j.replacer.PlaceholderReplacer;
import io.github.luizotavio.placeholder4j.replacer.PlaceholderReplacerImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class PlaceholderAPI {

    private final PlaceholderReplacer replacer;
    private final PlaceholderCache cache;

    private final Logger logger;

    public PlaceholderAPI() {
        this(
            Logger.getLogger("PlaceholderAPI"),
            new PlaceholderCacheImpl(
                Pattern.compile("%(.+?)%", Pattern.CASE_INSENSITIVE)
            )
        );
    }

    public PlaceholderAPI(@NotNull Logger logger, @NotNull PlaceholderCache cache) {
        this(
            new PlaceholderReplacerImpl(cache),
            cache,
            logger
        );
    } {

    }

    public PlaceholderAPI(@NotNull Logger logger, @NotNull PlaceholderReplacer replacer) {
        this(
            replacer,
            new PlaceholderCacheImpl(
                Pattern.compile("%(.+?)%", Pattern.CASE_INSENSITIVE)
            ),
            logger
        );
    }

    PlaceholderAPI(
        @NotNull PlaceholderReplacer replacer,
        @NotNull PlaceholderCache cache,
        @NotNull Logger logger
    ) {
        this.replacer = replacer;
        this.cache = cache;
        this.logger = logger;
    }

    public void register(@NotNull Placeholder<?> placeholder) {
        cache.register(placeholder);
    }

    public boolean remove(@NotNull String placeholder) {
        return cache.remove(placeholder);
    }


    @NotNull
    public String replace(@NotNull String text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (@NotNull Exception exception) {
            logger.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }

    @NotNull
    public String[] replace(@NotNull String[] text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (@NotNull Exception exception) {
            logger.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }

    @NotNull
    public Collection<String> replace(@NotNull Collection<String> text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (@NotNull Exception exception) {
            logger.severe("An error occurred while replacing placeholders: " + exception.getMessage());
        }

        return text;
    }
}
