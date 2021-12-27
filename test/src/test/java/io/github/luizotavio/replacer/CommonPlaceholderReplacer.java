package io.github.luizotavio.replacer;

import io.github.luizotavio.Placeholder;
import io.github.luizotavio.cache.CommonPlaceholderCache;
import io.github.luizotavio.exception.InternalPlaceholderException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class CommonPlaceholderReplacer implements PlaceholderReplacer<String> {

    private final CommonPlaceholderCache cache;
    private final Character placeholder;

    public CommonPlaceholderReplacer(char placeholder) {
        Pattern pattern = Pattern.compile(
          String.format("(%s)(.*)(%s)", placeholder, placeholder),
          Pattern.CASE_INSENSITIVE
        );

        this.placeholder = placeholder;

        this.cache = new CommonPlaceholderCache(pattern);
    }

    public CommonPlaceholderCache getCache() {
        return cache;
    }

    @NotNull
    @Override
    public String replace(@NotNull String text, @Nullable String value) throws InternalPlaceholderException {
        Set<Placeholder<?>> targets = cache.match(text);

        if (targets == null || targets.isEmpty()) {
            return text;
        }

        String mutable = text;

        try {
            for (Placeholder target : targets) {
                mutable = mutable.replace(
                  target.getName(),
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
    public Collection<String> replace(@NotNull Collection<String> collection, @Nullable String value) throws InternalPlaceholderException {
        List<String> base = new ArrayList<>(collection);

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
    public String[] replace(@NotNull String[] collection, @Nullable String value) throws InternalPlaceholderException {
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
