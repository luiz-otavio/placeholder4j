package io.github.luizotavio;

import io.github.luizotavio.cache.BukkitPlaceholderCache;
import io.github.luizotavio.placeholder.impl.BukkitPlaceholder;
import io.github.luizotavio.replacer.BukkitPlaceholderReplacer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

public class PlaceholderDelegator {

    private static final AtomicReference<BukkitFacade> FACADE_ATOMIC_REFERENCE = new AtomicReference<>();

    public static BukkitFacade createDelegator(Character character) {
        BukkitFacade bukkitFacade = new BukkitFacade(
          new BukkitPlaceholderReplacer(character)
        );

        FACADE_ATOMIC_REFERENCE.set(bukkitFacade);

        return bukkitFacade;
    }

    public static void register(BukkitPlaceholder... placeholders) {
        BukkitFacade bukkitFacade = ensureFacade();

        for (BukkitPlaceholder placeholder : placeholders) {
            bukkitFacade.register(placeholder);
        }
    }

    public static String replace(String input, Player player) {
        return ensureFacade().replace(input, player);
    }

    public static String[] replace(String[] input, @Nullable Player player) {
        return ensureFacade().replace(input, player);
    }

    public static Collection<String> replace(Collection<String> input, @Nullable Player player) {
        return ensureFacade().replace(input, player);
    }

    private static BukkitFacade ensureFacade() {
        BukkitFacade bukkitFacade = FACADE_ATOMIC_REFERENCE.get();

        if (bukkitFacade == null) {
            throw new NullPointerException("BukkitFacade is null");
        }

        return bukkitFacade;
    }



    public static class BukkitFacade {
        private final BukkitPlaceholderReplacer replacer;

        public BukkitFacade(BukkitPlaceholderReplacer replacer) {
            this.replacer = replacer;
        }

        public void register(BukkitPlaceholder placeholder) {
            BukkitPlaceholderCache cache = replacer.getCache();

            if (cache != null) {
                cache.register(placeholder);
            }
        }

        public String replace(String text, @Nullable Player player) {
            try {
                return replacer.replace(text, player);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

        public String[] replace(String[] text, @Nullable Player player) {
            try {
                return replacer.replace(text, player);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

        public Collection<String> replace(Collection<String> text, @Nullable Player player) {
            try {
                return replacer.replace(text, player);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return text;
        }

    }
}
