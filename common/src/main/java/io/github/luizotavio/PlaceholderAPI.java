package io.github.luizotavio;

import io.github.luizotavio.cache.DefaultPlaceholderCache;
import io.github.luizotavio.replacer.DefaultPlaceholderReplacer;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class PlaceholderAPI {

    private static final AtomicReference<PlaceholderDelegator> DELEGATOR_ATOMIC_REFERENCE = new AtomicReference<>();

    public static PlaceholderDelegator createDelegator(Character character) {
        PlaceholderDelegator bukkitFacade = new PlaceholderDelegator(
          new DefaultPlaceholderReplacer(character)
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
