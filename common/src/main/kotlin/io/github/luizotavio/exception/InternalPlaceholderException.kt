package io.github.luizotavio.exception

class InternalPlaceholderException(
    cause: Throwable
) : Exception(
    "Internal placeholder exception",
    cause
)