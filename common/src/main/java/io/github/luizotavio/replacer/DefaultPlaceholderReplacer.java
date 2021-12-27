package io.github.luizotavio.replacer;

import io.github.luizotavio.Placeholder;
import io.github.luizotavio.cache.DefaultPlaceholderCache;
import io.github.luizotavio.exception.InternalPlaceholderException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class DefaultPlaceholderReplacer implements PlaceholderReplacer {

    private final DefaultPlaceholderCache cache;

    public DefaultPlaceholderReplacer(char placeholder) {
        Pattern pattern = Pattern.compile(
          String.format("(%s)(.*)(%s)", placeholder, placeholder),
          Pattern.CASE_INSENSITIVE
        );

        this.cache = new DefaultPlaceholderCache(pattern);
    }

    public DefaultPlaceholderCache getCache() {
        return cache;
    }

    @NotNull
    @Override
    public String replace(@NotNull String text, Object... objects) throws InternalPlaceholderException {
        Set<Placeholder<?>> targets = cache.match(text);

        if (targets == null || targets.isEmpty()) {
            return text;
        }

        String mutable = text;

        try {
            for (Placeholder target : targets) {
                if (objects.length == 0) {
                    mutable = mutable.replace(
                      target.getName(),
                      target.resolve(null)
                    );

                    continue;
                }

                for (Object object : objects) {
                    if (object == null) {
                        continue;
                    }

                    if (!target.isCompatible(object)) continue;

                    mutable = mutable.replace(
                      target.getName(),
                      target.resolve(object)
                    );
                }
            }

            return mutable;
        } catch (Exception exception) {
            throw new InternalPlaceholderException(exception);
        }
    }

    @NotNull
    @Override
    public Collection<String> replace(@NotNull Collection<String> collection, Object... values) throws InternalPlaceholderException {
        List<String> base = new ArrayList<>(collection);

        for (String text : base) {
            if (text == null || text.isEmpty()) {
                continue;
            }

            base.set(
              base.indexOf(text),
              replace(text, values)
            );
        }

        return base;
    }

    @NotNull
    @Override
    public String[] replace(@NotNull String[] collection, Object... values) throws InternalPlaceholderException {
        for (int i = 0; i < collection.length; i++) {
            String text = collection[i];

            if (text == null || text.isEmpty()) {
                continue;
            }

            collection[i] = replace(text, values);
        }

        return collection;
    }
}
