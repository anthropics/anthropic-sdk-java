@file:JvmName("KmpOptionals")
package com.anthropic.core

/**
 * KMP Optional - a typealias to java.util.Optional on JVM.
 * On other platforms, this would be an actual Kotlin implementation.
 * 
 * Using typealias ensures zero overhead and full Java interop on JVM.
 */
typealias KmpOptional<T> = java.util.Optional<T>

// Kotlin best-practice extensions on top of Optional

/** Convert Optional to nullable */
fun <T> KmpOptional<T>.orNull(): T? = orElse(null)

/** Convert nullable to Optional */
fun <T : Any> T?.toOptional(): KmpOptional<T> = KmpOptional.ofNullable(this)

/** Fold: handle both present and empty cases */
inline fun <T, R> KmpOptional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent) ifPresent(get()) else ifEmpty()

/** Zip two Optionals */
inline fun <T, U, R : Any> KmpOptional<T>.zip(
    other: KmpOptional<U>, transform: (T, U) -> R
): KmpOptional<R> =
    if (isPresent && other.isPresent) KmpOptional.of(transform(get(), other.get()))
    else KmpOptional.empty()

/** Convert to List (0 or 1 elements) */
fun <T> KmpOptional<T>.toList(): List<T> = if (isPresent) listOf(get()) else emptyList()

/** Destructuring: val (value) = optional */
operator fun <T> KmpOptional<T>.component1(): T? = orElse(null)

/** Property-style emptiness check */
inline val <T> KmpOptional<T>.isEmpty: Boolean get() = !isPresent

/** Elvis-style with lazy default */
inline fun <T> KmpOptional<T>.getOrElse(default: () -> T): T =
    if (isPresent) get() else default()

/** Require or throw with message */
inline fun <T> KmpOptional<T>.require(message: String): T =
    orElseThrow { IllegalStateException(message) }
