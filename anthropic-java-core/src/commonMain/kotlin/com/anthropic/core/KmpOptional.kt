@file:JvmName("KmpOptionals")
package com.anthropic.core

/**
 * KMP Optional — mirrors the full java.util.Optional<T> API.
 *
 * On JVM: typealias to java.util.Optional<T> — zero overhead, full Java interop.
 * On non-JVM: will be a pure Kotlin implementation with the same API surface.
 *
 * This enables code in commonMain to use Optional uniformly across all targets.
 */
typealias KmpOptional<T> = java.util.Optional<T>

// ============================================================================
// Factory functions (mirror Optional.of / ofNullable / empty)
// ============================================================================

/** Create an Optional wrapping a non-null value. Mirrors [java.util.Optional.of]. */
fun <T : Any> optionalOf(value: T): KmpOptional<T> = KmpOptional.of(value)

/** Create an Optional from a nullable value. Mirrors [java.util.Optional.ofNullable]. */
fun <T : Any> optionalOfNullable(value: T?): KmpOptional<T> = KmpOptional.ofNullable(value)

/** Create an empty Optional. Mirrors [java.util.Optional.empty]. */
fun <T : Any> emptyOptional(): KmpOptional<T> = KmpOptional.empty<T>()

// ============================================================================
// Kotlin-idiomatic extensions (beyond java.util.Optional API)
// ============================================================================

/** Convert Optional to nullable. Equivalent to `.orElse(null)`. */
fun <T> KmpOptional<T>.orNull(): T? = orElse(null)

/** Convert nullable to Optional. */
fun <T : Any> T?.toOptional(): KmpOptional<T> = KmpOptional.ofNullable(this)

/** Fold: handle both present and empty cases with a single expression. */
inline fun <T, R> KmpOptional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent) ifPresent(get()) else ifEmpty()

/** Zip two Optionals — returns empty if either is empty. */
inline fun <T, U, R : Any> KmpOptional<T>.zip(
    other: KmpOptional<U>, transform: (T, U) -> R
): KmpOptional<R> =
    if (isPresent && other.isPresent) KmpOptional.of(transform(get(), other.get()))
    else KmpOptional.empty()

/** Convert to List (0 or 1 elements). */
fun <T> KmpOptional<T>.toList(): List<T> = if (isPresent) listOf(get()) else emptyList()

/** Destructuring: `val (value) = optional` — returns null if empty. */
operator fun <T> KmpOptional<T>.component1(): T? = orElse(null)

/** Property-style emptiness check. Mirrors [java.util.Optional.isEmpty] (Java 11+). */
inline val <T> KmpOptional<T>.isEmpty: Boolean get() = !isPresent

/** Elvis-style with lazy default. */
inline fun <T> KmpOptional<T>.getOrElse(default: () -> T): T =
    if (isPresent) get() else default()

/** Require value or throw with message. */
inline fun <T> KmpOptional<T>.require(message: String): T =
    orElseThrow { IllegalStateException(message) }

// ============================================================================
// Null-comparison operators for simplified usage
// ============================================================================

/** Check if Optional contains a value (equivalent to `!= null` for nullable). */
inline val <T> KmpOptional<T>.isNotEmpty: Boolean get() = isPresent

/** Execute block if present, return the Optional for chaining. */
inline fun <T> KmpOptional<T>.onPresent(block: (T) -> Unit): KmpOptional<T> = apply {
    if (isPresent) block(get())
}

/** Execute block if empty, return the Optional for chaining. */
inline fun <T> KmpOptional<T>.onEmpty(block: () -> Unit): KmpOptional<T> = apply {
    if (!isPresent) block()
}

/** Convert Optional to a Kotlin Sequence (0 or 1 elements). */
fun <T> KmpOptional<T>.asSequence(): Sequence<T> =
    if (isPresent) sequenceOf(get()) else emptySequence()

/** Convert Optional to a Flow (0 or 1 elements). Replaces java.util.Optional.stream(). */
fun <T> KmpOptional<T>.asFlow(): kotlinx.coroutines.flow.Flow<T> =
    if (isPresent) kotlinx.coroutines.flow.flowOf(get()) else kotlinx.coroutines.flow.emptyFlow()

// ============================================================================
// API surface documentation
// ============================================================================
//
// The following methods are available via the java.util.Optional typealias on JVM.
// When adding non-JVM targets, implement these in the pure Kotlin Optional class:
//
// Instance methods:
//   .isPresent: Boolean          — true if value is present
//   .isEmpty(): Boolean          — true if no value (Java 11+, also .isEmpty val above)
//   .get(): T                    — get value or throw NoSuchElementException
//   .orElse(other: T?): T        — get value or return other
//   .orElseGet(supplier): T      — get value or compute from supplier
//   .orElseThrow(): T            — get value or throw NoSuchElementException
//   .orElseThrow(supplier): T    — get value or throw supplied exception
//   .map(fn: (T) -> U?): Optional<U>     — transform value if present
//   .flatMap(fn: (T) -> Optional<U>): Optional<U> — transform and flatten
//   .filter(predicate: (T) -> Boolean): Optional<T> — keep if predicate matches
//   .ifPresent(consumer: (T) -> Unit)     — execute if present
//   .ifPresentOrElse(consumer, emptyAction) — execute one of two actions
//   .or(supplier: () -> Optional<T>): Optional<T> — alternative if empty (Java 9+)
//   .stream(): Stream<T>         — 0-or-1 element stream (use .asSequence() in KMP)
//
// Static factory methods (use top-level functions above in KMP):
//   Optional.of(value: T): Optional<T>           → optionalOf(value)
//   Optional.ofNullable(value: T?): Optional<T>   → optionalOfNullable(value)
//   Optional.empty(): Optional<T>                 → emptyOptional()
