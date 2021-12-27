package io.github.luizotavio.placeholder.impl;

import io.github.luizotavio.Placeholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VariablePlaceholder extends Placeholder {

    public VariablePlaceholder(@NotNull String name) {
        super(name);
    }

    @Override
    public boolean isCompatible(@NotNull Object value) {
        return true;
    }

    @NotNull
    @Override
    public String resolve(@Nullable Object consumer) {
        return String.valueOf(consumer);
    }
}
