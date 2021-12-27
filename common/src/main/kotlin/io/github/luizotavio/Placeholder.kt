package io.github.luizotavio

abstract class Placeholder<T>(
    val name: String,
    val author: String
) {

    abstract fun resolve(consumer: T?): String

}