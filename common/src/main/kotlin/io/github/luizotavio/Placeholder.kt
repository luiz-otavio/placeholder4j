package io.github.luizotavio

abstract class Placeholder<T>(
    val name: String
) {

    abstract fun resolve(consumer: T?): String

}