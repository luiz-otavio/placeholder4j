package io.github.luizotavio.replacer

import io.github.luizotavio.exception.InternalPlaceholderException

interface PlaceholderReplacer<T> {

    @Throws(InternalPlaceholderException::class)
    fun replace(text: String, value: T? = null): String

    @Throws(InternalPlaceholderException::class)
    fun replace(collection: Collection<String>, value: T? = null): Collection<String>

    @Throws(InternalPlaceholderException::class)
    fun replace(vararg collection: String, value: T? = null): Array<String>

}