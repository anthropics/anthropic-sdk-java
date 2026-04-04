@file:JvmName("OptionalJvm")
@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package com.anthropic.core

/**
 * JVM actual: typealias to java.util.Optional.
 *
 * Method signatures match exactly because the KMP functional interfaces
 * (Function, Supplier, Consumer, Predicate) typealias to java.util.function.*
 * on JVM, so java.util.Optional's method signatures are satisfied.
 *
 * Java callers get the real java.util.Optional with full API.
 */
actual typealias Optional<T> = java.util.Optional<T>

actual fun <T : Any> optionalOf(value: T): Optional<T> = java.util.Optional.of(value)

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> optionalOfNullable(value: T?): Optional<T> =
    java.util.Optional.ofNullable(value) as Optional<T>

@Suppress("UNCHECKED_CAST")
actual fun <T : Any> emptyOptional(): Optional<T> =
    java.util.Optional.empty<Any>() as Optional<T>
