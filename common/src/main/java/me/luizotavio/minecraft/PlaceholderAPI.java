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
package me.luizotavio.minecraft;

import me.luizotavio.minecraft.cache.DefaultPlaceholderCache;
import me.luizotavio.minecraft.replacer.DefaultPlaceholderReplacer;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Luiz Otávio de Farias Corrêa
 * @since 26/07/2022
 */
public class PlaceholderAPI {

    private static final AtomicReference<PlaceholderDelegator> DELEGATOR_ATOMIC_REFERENCE = new AtomicReference<>();

    public static PlaceholderDelegator createDelegator() {
        PlaceholderDelegator bukkitFacade = new PlaceholderDelegator(
          new DefaultPlaceholderReplacer()
        );

        DELEGATOR_ATOMIC_REFERENCE.set(bukkitFacade);

        return bukkitFacade;
    }

    public static void register(Placeholder<?>... placeholders) {
        PlaceholderDelegator bukkitFacade = ensureFacade();

        for (Placeholder<?> placeholder : placeholders) {
            bukkitFacade.register(placeholder);
        }
    }

    public static String replace(String input, Object... args) {
        return ensureFacade().replace(input, args);
    }

    public static String[] replace(String[] input, Object... args) {
        return ensureFacade().replace(input, args);
    }

    public static Collection<String> replace(Collection<String> input, Object... args) {
        return ensureFacade().replace(input, args);
    }

    private static PlaceholderDelegator ensureFacade() {
        PlaceholderDelegator bukkitFacade = DELEGATOR_ATOMIC_REFERENCE.get();

        if (bukkitFacade == null) {
            throw new NullPointerException("BukkitFacade is null");
        }

        return bukkitFacade;
    }

    public static class PlaceholderDelegator {
        private final DefaultPlaceholderReplacer replacer;

        public PlaceholderDelegator(DefaultPlaceholderReplacer replacer) {
            this.replacer = replacer;
        }

        public void register(Placeholder<?> placeholder) {
            DefaultPlaceholderCache cache = replacer.getCache();

            if (cache != null) {
                cache.register(placeholder);
            }
        }

        public String replace(String text, Object... args) {
            try {
                return replacer.replace(text, args);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

        public String[] replace(String[] text, Object... args) {
            try {
                return replacer.replace(text, args);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

        public Collection<String> replace(Collection<String> text, Object... args) {
            try {
                return replacer.replace(text, args);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

    }
}
