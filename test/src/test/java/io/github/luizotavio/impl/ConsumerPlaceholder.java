package io.github.luizotavio.impl;

import io.github.luizotavio.Placeholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class ConsumerPlaceholder extends Placeholder {

    private Supplier<Object> supplier;

    public ConsumerPlaceholder(@NotNull String name, Supplier<Object> supplier) {
        super(name);

        this.supplier = supplier;
    }

    @Override
    public boolean isCompatible(@NotNull Object value) {
        return true;
    }

    @NotNull
    @Override
    public String resolve(@Nullable Object consumer) {
        return String.valueOf(supplier.get());
    }
}
