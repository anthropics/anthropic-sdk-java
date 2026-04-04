@file:JvmName("KmpOptionals")
@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package com.anthropic.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapNotNull

/**
 * KMP Optional<T> — type-safe optional value container for all targets.
 *
 * On JVM: actual typealias to java.util.Optional<T> — zero overhead, full Java interop.
 * On JS/Wasm/Native: pure Kotlin implementation with the same API.
 *
 * Method signatures use KMP functional interfaces (Function, Supplier, Consumer,
 * Predicate) which typealias to java.util.function.* on JVM, giving exact
 * signature match with java.util.Optional for the actual typealias.
 *
 * The Optional pattern is well-known (Java, Swift, Rust, Scala) and provides
 * type safety on all targets including JS where the type system is weak.
 * Bridges gracefully into Kotlin collections (List, Set, Map, Sequence, Flow).
 */
expect class Optional<T : Any> {

    // --- Presence/value access ---

    /** Returns true if a value is present. */
    fun isPresent(): Boolean

    /** Returns the value if present, throws NoSuchElementException if empty. */
    fun get(): T

    /** Returns the value if present, otherwise returns [other]. */
    fun orElse(other: T?): T

    /** Returns the value if present, otherwise invokes [supplier] and returns its result. */
    fun orElseGet(supplier: Supplier<out T>): T

    /** Returns the value if present, otherwise throws the exception from [exceptionSupplier]. */
    fun <X : Throwable> orElseThrow(exceptionSupplier: Supplier<out X>): T

    // --- Transformation ---

    /** If present, apply [mapper] and wrap result. If mapper returns null, returns empty. */
    fun <U : Any> map(mapper: Function<in T, out U?>): Optional<U>

    /** If present, apply [mapper] which returns an Optional. */
    fun <U : Any> flatMap(mapper: Function<in T, out Optional<out U>>): Optional<U>

    /** If present and value matches [predicate], returns this; otherwise empty. */
    fun filter(predicate: Predicate<in T>): Optional<T>

    // --- Side effects ---

    /** If present, invoke [action] with the value. */
    fun ifPresent(action: Consumer<in T>)
}

// Kotlin property alias for isPresent() — enables `if (opt.isPresent)` syntax
inline val <T : Any> Optional<T>.isPresent: Boolean get() = isPresent()

// ============================================================================
// Factory functions — expect/actual per target
// ============================================================================

/** Create an Optional wrapping a non-null value. */
expect fun <T : Any> optionalOf(value: T): Optional<T>

/** Create an Optional from a nullable value. */
expect fun <T : Any> optionalOfNullable(value: T?): Optional<T>

/** Create an empty Optional. */
expect fun <T : Any> emptyOptional(): Optional<T>

// ============================================================================
// Core conversions — Optional ↔ nullable
// ============================================================================

/** Convert Optional to nullable. */
fun <T : Any> Optional<T>.orNull(): T? = if (isPresent()) get() else null

/** Convert nullable to Optional. */
fun <T : Any> T?.toOptional(): Optional<T> = optionalOfNullable(this)

/** Destructuring: `val (value) = optional` — returns null if empty. */
operator fun <T : Any> Optional<T>.component1(): T? = orNull()

/** Get value or null — alias for orNull(). */
fun <T : Any> Optional<T>.getOrNull(): T? = orNull()

// ============================================================================
// Presence/absence checks
// ============================================================================

/** Property-style emptiness check. */
inline val <T : Any> Optional<T>.isEmpty: Boolean get() = !isPresent()

/** Property-style presence check. */
inline val <T : Any> Optional<T>.isNotEmpty: Boolean get() = isPresent()

// ============================================================================
// Kotlin-idiomatic transformations (beyond java.util.Optional API)
// ============================================================================

/** Fold: exhaustively handle both present and empty cases. */
inline fun <T : Any, R> Optional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent()) ifPresent(get()) else ifEmpty()

/** Elvis-style with lazy default. */
inline fun <T : Any> Optional<T>.getOrElse(default: () -> T): T =
    if (isPresent()) get() else default()

/** Require value or throw with message. */
inline fun <T : Any> Optional<T>.require(message: String): T =
    orElseThrow(toSupplier { IllegalStateException(message) })

// ============================================================================
// Chaining callbacks
// ============================================================================

/** Execute block if present, return the Optional for chaining. */
inline fun <T : Any> Optional<T>.onPresent(block: (T) -> Unit): Optional<T> = apply {
    if (isPresent()) block(get())
}

/** Execute block if empty, return the Optional for chaining. */
inline fun <T : Any> Optional<T>.onEmpty(block: () -> Unit): Optional<T> = apply {
    if (!isPresent()) block()
}

// ============================================================================
// Collection bridge — Optional ↔ List, Set, Map, Sequence, Flow
// ============================================================================

/** Convert to List (0 or 1 elements). Type-safe on all targets including JS. */
fun <T : Any> Optional<T>.toList(): List<T> =
    if (isPresent()) listOf(get()) else emptyList()

/** Convert to Set (0 or 1 elements). */
fun <T : Any> Optional<T>.toSet(): Set<T> =
    if (isPresent()) setOf(get()) else emptySet()

/** Convert to Sequence (0 or 1 elements). */
fun <T : Any> Optional<T>.asSequence(): Sequence<T> =
    if (isPresent()) sequenceOf(get()) else emptySequence()

/** Convert to Flow (0 or 1 elements). */
fun <T : Any> Optional<T>.asFlow(): Flow<T> =
    if (isPresent()) flowOf(get()) else emptyFlow()

/** Flow-based stream — KMP equivalent of java.util.Optional.stream(). */
fun <T : Any> Optional<T>.stream(): Flow<T> = asFlow()

/** Zip two Optionals — returns empty if either is empty. */
inline fun <T : Any, U : Any, R : Any> Optional<T>.zip(
    other: Optional<U>, transform: (T, U) -> R
): Optional<R> =
    if (isPresent() && other.isPresent()) optionalOf(transform(get(), other.get()))
    else emptyOptional()

// ============================================================================
// Collection → Optional — extract from collections type-safely
// ============================================================================

/** Get map value as Optional — type-safe alternative to map[key]. */
fun <K, V : Any> Map<K, V>.getOptional(key: K): Optional<V> =
    optionalOfNullable(get(key))

/** First element as Optional. */
fun <T : Any> Iterable<T>.firstOptional(): Optional<T> =
    optionalOfNullable(firstOrNull())

/** First matching element as Optional. */
inline fun <T : Any> Iterable<T>.firstOptional(predicate: (T) -> Boolean): Optional<T> =
    optionalOfNullable(firstOrNull(predicate))

/** Last element as Optional. */
fun <T : Any> List<T>.lastOptional(): Optional<T> =
    optionalOfNullable(lastOrNull())

/** Single element as Optional — empty if 0 or 2+ elements. */
fun <T : Any> Iterable<T>.singleOptional(): Optional<T> =
    optionalOfNullable(singleOrNull())

// ============================================================================
// Optional collections — filter/unwrap collections of Optional
// ============================================================================

/** Filter a list of Optionals to only present values. */
fun <T : Any> Iterable<Optional<T>>.filterPresent(): List<T> =
    mapNotNull { it.orNull() }

/** Filter a sequence of Optionals to only present values. */
fun <T : Any> Sequence<Optional<T>>.filterPresent(): Sequence<T> =
    mapNotNull { it.orNull() }

/** Filter a flow of Optionals to only present values. */
fun <T : Any> Flow<Optional<T>>.filterPresent(): Flow<T> =
    mapNotNull { it.orNull() }

/** Convert List of Pairs with Optional values to Map, discarding empties. */
fun <K, V : Any> Iterable<Pair<K, Optional<V>>>.toOptionalMap(): Map<K, V> =
    mapNotNull { (k, v) -> v.orNull()?.let { k to it } }.toMap()

// ============================================================================
// Multimap support
// ============================================================================

/** Get all values for a key, returning empty list if absent. */
fun <K, V> Map<K, List<V>>.getAll(key: K): List<V> =
    getOrDefault(key, emptyList())

/** Get first value for a key as Optional. */
fun <K, V : Any> Map<K, List<V>>.getFirst(key: K): Optional<V> =
    optionalOfNullable(get(key)?.firstOrNull())
