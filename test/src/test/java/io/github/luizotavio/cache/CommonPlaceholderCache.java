package io.github.luizotavio.cache;

import io.github.luizotavio.Placeholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonPlaceholderCache extends AbstractPlaceholderCache {

    private final Map<String, Placeholder<String>> placeholders;

    public CommonPlaceholderCache(@NotNull Pattern pattern) {
        super(pattern);

        placeholders = new LinkedHashMap<>();
    }

    @Nullable
    @Override
    public Placeholder<?> get(@NotNull String key) {
        return placeholders.get(key);
    }

    @Override
    public void register(@NotNull Placeholder<?> placeholder) {
        placeholders.put(placeholder.getName(), (Placeholder<String>) placeholder);
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

        Set<Placeholder<?>> collection = new HashSet<>();

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
