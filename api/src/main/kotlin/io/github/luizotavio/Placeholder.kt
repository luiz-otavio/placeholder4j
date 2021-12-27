package io.github.luizotavio

abstract class Placeholder<T>(
    val name: String
) {

    abstract fun isCompatible(value: Any): Boolean

    abstract fun resolve(consumer: T?): String

}