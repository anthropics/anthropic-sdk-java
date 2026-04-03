@file:JvmName("KmpOptionals")
package com.anthropic.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapNotNull

/**
 * KMP Optional — type-safe Optional<T> that mirrors the java.util.Optional API.
 *
 * On JVM: typealias to java.util.Optional<T> — zero overhead, full Java interop.
 * On non-JVM (JS/Wasm/Native): will be a pure Kotlin class with the same API.
 *
 * Optional provides type safety across all targets, including JS where the type
 * system is weak. It bridges gracefully into Kotlin collections (List, Set, Map,
 * Sequence, Flow) giving a uniform API for presence/absence semantics.
 *
 * The Optional pattern is a well-known design pattern (Java, Swift, Rust, Scala)
 * that complements Kotlin's nullable types by providing an explicit container
 * with rich transformation methods.
 */
typealias KmpOptional<T> = java.util.Optional<T>

// ============================================================================
// Factory functions — portable across all targets
// ============================================================================

/** Create an Optional wrapping a non-null value. */
fun <T : Any> optionalOf(value: T): KmpOptional<T> = KmpOptional.of(value)

/** Create an Optional from a nullable value. */
fun <T : Any> optionalOfNullable(value: T?): KmpOptional<T> = KmpOptional.ofNullable(value)

/** Create an empty Optional. */
fun <T : Any> emptyOptional(): KmpOptional<T> = KmpOptional.empty<T>()

// ============================================================================
// Core conversions — Optional ↔ nullable
// ============================================================================

/** Convert Optional to nullable. */
fun <T> KmpOptional<T>.orNull(): T? = orElse(null)

/** Convert nullable to Optional. */
fun <T : Any> T?.toOptional(): KmpOptional<T> = KmpOptional.ofNullable(this)

/** Destructuring: `val (value) = optional` — returns null if empty. */
operator fun <T> KmpOptional<T>.component1(): T? = orElse(null)

// ============================================================================
// Presence/absence checks
// ============================================================================

/** Property-style emptiness check. */
inline val <T> KmpOptional<T>.isEmpty: Boolean get() = !isPresent

/** Property-style presence check. */
inline val <T> KmpOptional<T>.isNotEmpty: Boolean get() = isPresent

// ============================================================================
// Functional transformations — mirrors java.util.Optional API
// ============================================================================

/** Fold: exhaustively handle both present and empty cases. */
inline fun <T, R> KmpOptional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent) ifPresent(get()) else ifEmpty()

/** Elvis-style with lazy default. */
inline fun <T> KmpOptional<T>.getOrElse(default: () -> T): T =
    if (isPresent) get() else default()

/** Require value or throw with message. */
inline fun <T> KmpOptional<T>.require(message: String): T =
    orElseThrow { IllegalStateException(message) }

// ============================================================================
// Chaining callbacks
// ============================================================================

/** Execute block if present, return the Optional for chaining. */
inline fun <T> KmpOptional<T>.onPresent(block: (T) -> Unit): KmpOptional<T> = apply {
    if (isPresent) block(get())
}

/** Execute block if empty, return the Optional for chaining. */
inline fun <T> KmpOptional<T>.onEmpty(block: () -> Unit): KmpOptional<T> = apply {
    if (!isPresent) block()
}

// ============================================================================
// Collection bridge — Optional ↔ List, Set, Map, Sequence, Flow
// ============================================================================

/** Convert to List (0 or 1 elements). Type-safe on all targets including JS. */
fun <T> KmpOptional<T>.toList(): List<T> =
    if (isPresent) listOf(get()) else emptyList()

/** Convert to Set (0 or 1 elements). */
fun <T> KmpOptional<T>.toSet(): Set<T> =
    if (isPresent) setOf(get()) else emptySet()

/** Convert to Sequence (0 or 1 elements). Lazy evaluation. */
fun <T> KmpOptional<T>.asSequence(): Sequence<T> =
    if (isPresent) sequenceOf(get()) else emptySequence()

/** Convert to Flow (0 or 1 elements). Replaces java.util.Optional.stream(). */
fun <T> KmpOptional<T>.asFlow(): Flow<T> =
    if (isPresent) flowOf(get()) else emptyFlow()

/** Zip two Optionals — returns empty if either is empty. */
inline fun <T, U, R : Any> KmpOptional<T>.zip(
    other: KmpOptional<U>, transform: (T, U) -> R
): KmpOptional<R> =
    if (isPresent && other.isPresent) optionalOf(transform(get(), other.get()))
    else emptyOptional()

// ============================================================================
// Collection → Optional — extract from collections type-safely
// ============================================================================

/** Get map value as Optional — type-safe alternative to map[key] which returns nullable. */
fun <K, V : Any> Map<K, V>.getOptional(key: K): KmpOptional<V> =
    optionalOfNullable(get(key))

/** First element as Optional — empty if collection is empty. */
fun <T : Any> Iterable<T>.firstOptional(): KmpOptional<T> =
    optionalOfNullable(firstOrNull())

/** First matching element as Optional. */
inline fun <T : Any> Iterable<T>.firstOptional(predicate: (T) -> Boolean): KmpOptional<T> =
    optionalOfNullable(firstOrNull(predicate))

/** Last element as Optional. */
fun <T : Any> List<T>.lastOptional(): KmpOptional<T> =
    optionalOfNullable(lastOrNull())

/** Single element as Optional — empty if 0 or 2+ elements. */
fun <T : Any> Iterable<T>.singleOptional(): KmpOptional<T> =
    optionalOfNullable(singleOrNull())

// ============================================================================
// Optional collections — work with List<Optional<T>> / Flow<Optional<T>>
// ============================================================================

/** Filter a list of Optionals to only present values. Type-safe unwrap. */
fun <T : Any> Iterable<KmpOptional<T>>.filterPresent(): List<T> =
    mapNotNull { it.orNull() }

/** Filter a sequence of Optionals to only present values. */
fun <T : Any> Sequence<KmpOptional<T>>.filterPresent(): Sequence<T> =
    mapNotNull { it.orNull() }

/** Filter a flow of Optionals to only present values. */
fun <T : Any> Flow<KmpOptional<T>>.filterPresent(): Flow<T> =
    mapNotNull { it.orNull() }

/** Convert a List of Optionals to a Map (key → value), discarding empties. */
fun <K, V : Any> Iterable<Pair<K, KmpOptional<V>>>.toOptionalMap(): Map<K, V> =
    mapNotNull { (k, v) -> v.orNull()?.let { k to it } }.toMap()

// ============================================================================
// Multimap support — Optional with collection values
// ============================================================================

/** Get all values for a key, returning empty list if absent. */
fun <K, V> Map<K, List<V>>.getAll(key: K): List<V> =
    getOrDefault(key, emptyList())

/** Get first value for a key as Optional. Common multimap pattern. */
fun <K, V : Any> Map<K, List<V>>.getFirst(key: K): KmpOptional<V> =
    optionalOfNullable(get(key)?.firstOrNull())

// ============================================================================
// JVM API surface documentation (for non-JVM implementation reference)
// ============================================================================
//
// These methods are available via the java.util.Optional typealias on JVM.
// When adding non-JVM targets, implement these in the pure Kotlin Optional class:
//
// Instance methods (from java.util.Optional):
//   .isPresent: Boolean
//   .get(): T
//   .orElse(other: T?): T
//   .orElseGet(supplier: () -> T): T
//   .orElseThrow(): T
//   .orElseThrow(supplier: () -> Throwable): T
//   .map(fn: (T) -> U?): Optional<U>
//   .flatMap(fn: (T) -> Optional<U>): Optional<U>
//   .filter(predicate: (T) -> Boolean): Optional<T>
//   .ifPresent(consumer: (T) -> Unit)
//   .ifPresentOrElse(consumer: (T) -> Unit, emptyAction: () -> Unit)
//   .or(supplier: () -> Optional<T>): Optional<T>
//
// KMP equivalents for Stream:
//   .stream()       → use .asFlow() or .asSequence()
//   .toList()       → already provided above
//   .toSet()        → already provided above
