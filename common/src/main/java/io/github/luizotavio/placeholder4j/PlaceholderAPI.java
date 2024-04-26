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

    private static final AtomicReference<PlaceholderAPI> DELEGATOR_ATOMIC_REFERENCE = new AtomicReference<>();

    private final DefaultPlaceholderReplacer replacer;

    public PlaceholderAPI() {
        replacer = null;

        DELEGATOR_ATOMIC_REFERENCE.accumulateAndGet(this, (prev, current) -> {
            if (prev == null) {
                LOGGER.info("Initializing PlaceholderAPI...");

                return new PlaceholderAPI(
                    new DefaultPlaceholderReplacer()
                );
            } else {
                LOGGER.info("PlaceholderAPI already initialized!");
                return prev;
            }
        });
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
            exception.printStackTrace();
        }

        return text;
    }

    public String[] replace(@NotNull String[] text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return text;
    }

    public Collection<String> replace(@NotNull Collection<String> text, @Nullable Object... args) {
        try {
            return replacer.replace(text, args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return text;
    }
}
