package io.github.luizotavio.replacer

interface PlaceholderReplacer<T> {

    fun replace(placeholder: String, value: T? = null): String

    fun replace(collection: Collection<String>, value: T? = null): Collection<String>

    fun replace(vararg collection: String, value: T? = null): Collection<String>

}