package io.github.luizotavio.cache;

import com.google.common.collect.Sets;
import io.github.luizotavio.Placeholder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BukkitPlaceholderCache extends AbstractPlaceholderCache {

    private final Map<String, Placeholder<Player>> placeholders;

    public BukkitPlaceholderCache(@NotNull Pattern pattern) {
        super(pattern);

        placeholders = new HashMap<>();
    }

    @Nullable
    @Override
    public Placeholder<?> get(@NotNull String key) {
        return placeholders.get(key);
    }

    @Override
    public void register(@NotNull Placeholder<?> placeholder) {
        placeholders.put(placeholder.getName(), (Placeholder<Player>) placeholder);
    }

    @Override
    public boolean exists(@NotNull String key) {
        return placeholders.containsKey(key);
    }

    @Nullable
    @Override
    public Set<Placeholder<?>> match(@NotNull String message) {
        if (message.isEmpty()) {
            return Collections.emptySet();
        }

        Set<Placeholder<?>> collection = Sets.newHashSet();

        Matcher matcher = getPattern().matcher(message);

        while (matcher.find()) {
            String key = matcher.group();

            if (key.isEmpty()) {
                return null;
            }

            Placeholder<?> placeholder = placeholders.get(key);

            if (placeholder != null) {
                collection.add(placeholder);
            }
        }

        return collection;
    }
}
