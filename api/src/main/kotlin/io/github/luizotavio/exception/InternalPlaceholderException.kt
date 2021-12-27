package io.github.luizotavio.exception

class InternalPlaceholderException : Exception {
    constructor(throwable: Throwable) : super(throwable)

    constructor(message: String) : super(message)
}