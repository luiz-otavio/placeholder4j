package io.github.luizotavio.replacer

import io.github.luizotavio.exception.InternalPlaceholderException

interface PlaceholderReplacer {

    @Throws(InternalPlaceholderException::class)
    fun replace(text: String, vararg values: Any): String

    @Throws(InternalPlaceholderException::class)
    fun replace(collection: Collection<String>, vararg values: Any): Collection<String>

    @Throws(InternalPlaceholderException::class)
    fun replace(collection: Array<String>, vararg values: Any): Array<String>

}