package io.github.luizotavio.cache

import io.github.luizotavio.Placeholder
import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class AbstractPlaceholderCache(
    val pattern: Pattern
) {

    abstract fun get(key: String): Placeholder<*>?

    abstract fun register(placeholder: Placeholder<*>)

    // Change to return the matcher instead of alot of placeholders
    abstract fun match(message: String): Matcher?

    abstract fun exists(key: String): Boolean

}