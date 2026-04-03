@file:JvmName("OptionalExtensions")

package com.anthropic.core

import java.util.Optional

/** Convert Optional to nullable */
inline fun <T> Optional<T>.orNull(): T? = orElse(null)

/** Convert nullable to Optional */
inline fun <T : Any> T?.toOptional(): Optional<T> = Optional.ofNullable(this)

/** Fold: handle both present and empty */
inline fun <T, R> Optional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent) ifPresent(get()) else ifEmpty()

/** Zip two Optionals */
inline fun <T, U, R : Any> Optional<T>.zip(
    other: Optional<U>, transform: (T, U) -> R
): Optional<R> =
    if (isPresent && other.isPresent) Optional.of(transform(get(), other.get()))
    else Optional.empty()

/** Convert to List (0 or 1 elements) */
fun <T> Optional<T>.toList(): List<T> = if (isPresent) listOf(get()) else emptyList()

/** Convert to Sequence */
fun <T> Optional<T>.asSequence(): Sequence<T> =
    if (isPresent) sequenceOf(get()) else emptySequence()

/** Destructuring: val (value) = optional */
operator fun <T> Optional<T>.component1(): T? = orElse(null)

/** Property-style emptiness */
inline val <T> Optional<T>.isEmpty: Boolean get() = !isPresent

/** Elvis-style with lazy default */
inline fun <T> Optional<T>.getOrElse(default: () -> T): T =
    if (isPresent) get() else default()

/** Require or throw */
inline fun <T> Optional<T>.require(message: String): T =
    orElseThrow { IllegalStateException(message) }

