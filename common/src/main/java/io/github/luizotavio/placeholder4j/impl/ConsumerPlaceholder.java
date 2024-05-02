/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>5.
 */
package io.github.luizotavio.placeholder4j.impl;

import io.github.luizotavio.placeholder4j.AbstractPlaceholder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
public class ConsumerPlaceholder<T> extends AbstractPlaceholder<T> {

    private final Class<T> type;

    private final Supplier<T> supplier;

    public ConsumerPlaceholder(@NotNull String name, @NotNull Class<T> clazz, @NotNull Supplier<T> supplier) {
        super(name);

        this.type = clazz;
        this.supplier = supplier;
    }

    @Override
    public boolean isCompatible(@NotNull Object value) {
        return type.isInstance(value);
    }

    @NotNull
    @Override
    public String resolve(@Nullable Object consumer) {
        return String.valueOf(supplier.get());
    }

}
