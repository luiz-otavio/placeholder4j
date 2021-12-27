package io.github.luizotavio.placeholder;

import io.github.luizotavio.Placeholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CommonPlaceholder extends Placeholder<String> {
    private Object value;

    public CommonPlaceholder(@NotNull String name, Object value) {
        super(name, "abc");

        this.value = value;
    }

    @NotNull
    @Override
    public String resolve(@Nullable String consumer) {
        return String.valueOf(value);
    }
}
