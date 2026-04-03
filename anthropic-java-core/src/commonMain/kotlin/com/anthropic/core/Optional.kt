package com.anthropic.core

/**
 * KMP Optional<T> — on JVM typealiased to java.util.Optional<T>.
 * On other platforms, a simple Kotlin implementation.
 */
expect class Optional<T> {
    fun isPresent(): Boolean
    fun get(): T
    fun orElse(other: T): T
}

expect fun <T : Any> emptyOptional(): Optional<T>
expect fun <T : Any> optionalOf(value: T): Optional<T>
expect fun <T : Any> optionalOfNullable(value: T?): Optional<T>
expect fun <T : Any> Optional<T>.getOrNull(): T?
