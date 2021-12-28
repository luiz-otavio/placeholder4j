package io.github.luizotavio;

import io.github.luizotavio.cache.DefaultPlaceholderCache;
import io.github.luizotavio.replacer.DefaultPlaceholderReplacer;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class PlaceholderDelegator {

    private static final AtomicReference<PlaceholderFacade> FACADE_ATOMIC_REFERENCE = new AtomicReference<>();

    public static PlaceholderFacade createDelegator(Character character) {
        PlaceholderFacade bukkitFacade = new PlaceholderFacade(
          new DefaultPlaceholderReplacer(character)
        );

        FACADE_ATOMIC_REFERENCE.set(bukkitFacade);

        return bukkitFacade;
    }

    public static void register(Placeholder<?>... placeholders) {
        PlaceholderFacade bukkitFacade = ensureFacade();

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

    private static PlaceholderFacade ensureFacade() {
        PlaceholderFacade bukkitFacade = FACADE_ATOMIC_REFERENCE.get();

        if (bukkitFacade == null) {
            throw new NullPointerException("BukkitFacade is null");
        }

        return bukkitFacade;
    }

    public static class PlaceholderFacade {
        private final DefaultPlaceholderReplacer replacer;

        public PlaceholderFacade(DefaultPlaceholderReplacer replacer) {
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
