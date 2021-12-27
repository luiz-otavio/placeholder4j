package io.github.luizotavio.placeholder.impl;

import io.github.luizotavio.Placeholder;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public abstract class BukkitPlaceholder extends Placeholder<Player> {
    public BukkitPlaceholder(@NotNull String name) {
        super(name);
    }
}
