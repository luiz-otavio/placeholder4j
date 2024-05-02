package io.github.luizotavio.placeholder4j.cache

import java.util.regex.Pattern

/**
 * @author Luiz O. F. Corrêa
 * @since 02/05/2024
 */
/**
 * Abstract class to be implemented by the library to create a placeholder cache.
 * The cache is used to store placeholders and replace them with values.
 * @see PlaceholderCache
 */
abstract class AbstractPlaceholderCache(
    protected val pattern: Pattern
) : PlaceholderCache {
    override fun getPattern(): Pattern = pattern

}
