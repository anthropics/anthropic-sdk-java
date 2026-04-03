@file:JvmName("OptionalJvm")
@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package com.anthropic.core

/**
 * JVM actual: typealias to java.util.Optional.
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

// ============================================================================
// JVM actual extension functions — delegate to java.util.Optional methods.
// On JVM, Kotlin SAM-converts lambdas to Function/Supplier/Consumer/Predicate.
// ============================================================================

@Suppress("UNCHECKED_CAST")
actual fun <T : Any, U : Any> Optional<T>.mapOptional(mapper: (T) -> U?): Optional<U> =
    map(mapper) as Optional<U>

actual fun <T : Any, U : Any> Optional<T>.flatMapOptional(mapper: (T) -> Optional<U>): Optional<U> =
    flatMap(mapper)

actual fun <T : Any> Optional<T>.filterOptional(predicate: (T) -> Boolean): Optional<T> =
    filter(predicate)

actual fun <T : Any> Optional<T>.ifPresentOptional(action: (T) -> Unit) =
    ifPresent(action)

actual fun <T : Any> Optional<T>.orElseGet(supplier: () -> T): T =
    orElseGet(supplier)

actual fun <T : Any, X : Throwable> Optional<T>.orElseThrow(exceptionSupplier: () -> X): T =
    orElseThrow(exceptionSupplier)
