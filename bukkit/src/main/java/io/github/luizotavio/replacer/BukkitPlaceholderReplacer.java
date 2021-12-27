package io.github.luizotavio.replacer;

import com.google.common.collect.Lists;
import io.github.luizotavio.Placeholder;
import io.github.luizotavio.cache.BukkitPlaceholderCache;
import io.github.luizotavio.exception.InternalPlaceholderException;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class BukkitPlaceholderReplacer implements PlaceholderReplacer<Player> {

    private final BukkitPlaceholderCache cache;
    private final Character placeholder;

    public BukkitPlaceholderReplacer(char placeholder) {
        Pattern pattern = Pattern.compile(
          String.format("(%s)(.*)(%s)", placeholder, placeholder),
          Pattern.CASE_INSENSITIVE
        );

        this.placeholder = placeholder;

        this.cache = new BukkitPlaceholderCache(pattern);
    }

    public BukkitPlaceholderCache getCache() {
        return cache;
    }

    @NotNull
    @Override
    public String replace(@NotNull String text, @Nullable Player value) throws InternalPlaceholderException {
        Set<Placeholder<?>> targets = cache.match(text);

        if (targets == null || targets.isEmpty()) {
            return text;
        }

        String mutable = text;

        try {
            for (Placeholder target : targets) {
                mutable = mutable.replace(
                  String.format("%s%s%s", placeholder, target.getName(), placeholder),
                  target.resolve(value)
                );
            }

            return mutable;
        } catch (Exception exception) {
            throw new InternalPlaceholderException(exception);
        }
    }

    @NotNull
    @Override
    public Collection<String> replace(@NotNull Collection<String> collection, @Nullable Player value) throws InternalPlaceholderException {
        List<String> base = Lists.newArrayList(collection);

        for (String text : base) {
            if (text == null || text.isEmpty()) {
                continue;
            }

            base.set(
              base.indexOf(text),
              replace(text, value)
            );
        }

        return base;
    }

    @NotNull
    @Override
    public String[] replace(@NotNull String[] collection, @Nullable Player value) throws InternalPlaceholderException {
        for (int i = 0; i < collection.length; i++) {
            String text = collection[i];

            if (text == null || text.isEmpty()) {
                continue;
            }

            collection[i] = replace(text, value);
        }

        return collection;
    }
}
