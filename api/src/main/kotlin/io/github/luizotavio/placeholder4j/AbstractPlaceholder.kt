package io.github.luizotavio.placeholder4j

abstract class AbstractPlaceholder<T>(
    private val name: String
) : Placeholder<T> {

    override fun getName(): String = name

}
