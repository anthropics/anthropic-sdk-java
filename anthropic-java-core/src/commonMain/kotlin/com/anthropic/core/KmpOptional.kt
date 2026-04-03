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
 * The Optional pattern is well-known (Java, Swift, Rust, Scala) and provides
 * type safety on all targets including JS where the type system is weak.
 * Bridges gracefully into Kotlin collections (List, Set, Map, Sequence, Flow).
 */
expect class Optional<T : Any> {
    /** Returns true if a value is present. */
    fun isPresent(): Boolean
    /** Returns the value if present, throws NoSuchElementException if empty. */
    fun get(): T
    /** Returns the value if present, otherwise returns [other]. */
    fun orElse(other: T?): T
}

// Kotlin property alias for isPresent()
inline val <T : Any> Optional<T>.isPresent: Boolean get() = isPresent()

// ============================================================================
// Factory functions — expect/actual per target
// ============================================================================

expect fun <T : Any> optionalOf(value: T): Optional<T>
expect fun <T : Any> optionalOfNullable(value: T?): Optional<T>
expect fun <T : Any> emptyOptional(): Optional<T>

// ============================================================================
// Core API — expect/actual extension functions (match java.util.Optional API)
// SAM types (Function, Supplier, Consumer, Predicate) can't be in expect class,
// so these are top-level expect functions with Kotlin lambda signatures.
// ============================================================================

/** Transform value if present. Mirrors java.util.Optional.map(). */
expect fun <T : Any, U : Any> Optional<T>.mapOptional(mapper: (T) -> U?): Optional<U>

/** Transform and flatten. Mirrors java.util.Optional.flatMap(). */
expect fun <T : Any, U : Any> Optional<T>.flatMapOptional(mapper: (T) -> Optional<U>): Optional<U>

/** Keep value if predicate matches. Mirrors java.util.Optional.filter(). */
expect fun <T : Any> Optional<T>.filterOptional(predicate: (T) -> Boolean): Optional<T>

/** Execute action if present. Mirrors java.util.Optional.ifPresent(). */
expect fun <T : Any> Optional<T>.ifPresentOptional(action: (T) -> Unit)

/** Get value or compute from supplier. Mirrors java.util.Optional.orElseGet(). */
expect fun <T : Any> Optional<T>.orElseGet(supplier: () -> T): T

/** Get value or throw supplied exception. Mirrors java.util.Optional.orElseThrow(). */
expect fun <T : Any, X : Throwable> Optional<T>.orElseThrow(exceptionSupplier: () -> X): T

// ============================================================================
// Core conversions — Optional ↔ nullable
// ============================================================================

fun <T : Any> Optional<T>.orNull(): T? = if (isPresent()) get() else null
fun <T : Any> T?.toOptional(): Optional<T> = optionalOfNullable(this)
operator fun <T : Any> Optional<T>.component1(): T? = orNull()
fun <T : Any> Optional<T>.getOrNull(): T? = orNull()

// ============================================================================
// Presence/absence checks
// ============================================================================

inline val <T : Any> Optional<T>.isEmpty: Boolean get() = !isPresent()
inline val <T : Any> Optional<T>.isNotEmpty: Boolean get() = isPresent()

// ============================================================================
// Functional transformations
// ============================================================================

inline fun <T : Any, R> Optional<T>.fold(ifEmpty: () -> R, ifPresent: (T) -> R): R =
    if (isPresent()) ifPresent(get()) else ifEmpty()

inline fun <T : Any> Optional<T>.getOrElse(default: () -> T): T =
    if (isPresent()) get() else default()

inline fun <T : Any> Optional<T>.require(message: String): T =
    orElseThrow { IllegalStateException(message) }

// ============================================================================
// Chaining callbacks
// ============================================================================

inline fun <T : Any> Optional<T>.onPresent(block: (T) -> Unit): Optional<T> = apply {
    if (isPresent()) block(get())
}

inline fun <T : Any> Optional<T>.onEmpty(block: () -> Unit): Optional<T> = apply {
    if (!isPresent()) block()
}

// ============================================================================
// Collection bridge — Optional ↔ List, Set, Map, Sequence, Flow
// ============================================================================

fun <T : Any> Optional<T>.toList(): List<T> =
    if (isPresent()) listOf(get()) else emptyList()

fun <T : Any> Optional<T>.toSet(): Set<T> =
    if (isPresent()) setOf(get()) else emptySet()

fun <T : Any> Optional<T>.asSequence(): Sequence<T> =
    if (isPresent()) sequenceOf(get()) else emptySequence()

fun <T : Any> Optional<T>.asFlow(): Flow<T> =
    if (isPresent()) flowOf(get()) else emptyFlow()

/** Flow-based stream — KMP equivalent of java.util.Optional.stream(). */
fun <T : Any> Optional<T>.stream(): Flow<T> = asFlow()

inline fun <T : Any, U : Any, R : Any> Optional<T>.zip(
    other: Optional<U>, transform: (T, U) -> R
): Optional<R> =
    if (isPresent() && other.isPresent()) optionalOf(transform(get(), other.get()))
    else emptyOptional()

// ============================================================================
// Collection → Optional
// ============================================================================

fun <K, V : Any> Map<K, V>.getOptional(key: K): Optional<V> =
    optionalOfNullable(get(key))

fun <T : Any> Iterable<T>.firstOptional(): Optional<T> =
    optionalOfNullable(firstOrNull())

inline fun <T : Any> Iterable<T>.firstOptional(predicate: (T) -> Boolean): Optional<T> =
    optionalOfNullable(firstOrNull(predicate))

fun <T : Any> List<T>.lastOptional(): Optional<T> =
    optionalOfNullable(lastOrNull())

fun <T : Any> Iterable<T>.singleOptional(): Optional<T> =
    optionalOfNullable(singleOrNull())

// ============================================================================
// Optional collections
// ============================================================================

fun <T : Any> Iterable<Optional<T>>.filterPresent(): List<T> =
    mapNotNull { it.orNull() }

fun <T : Any> Sequence<Optional<T>>.filterPresent(): Sequence<T> =
    mapNotNull { it.orNull() }

fun <T : Any> Flow<Optional<T>>.filterPresent(): Flow<T> =
    mapNotNull { it.orNull() }

fun <K, V : Any> Iterable<Pair<K, Optional<V>>>.toOptionalMap(): Map<K, V> =
    mapNotNull { (k, v) -> v.orNull()?.let { k to it } }.toMap()

// ============================================================================
// Multimap support
// ============================================================================

fun <K, V> Map<K, List<V>>.getAll(key: K): List<V> =
    getOrDefault(key, emptyList())

fun <K, V : Any> Map<K, List<V>>.getFirst(key: K): Optional<V> =
    optionalOfNullable(get(key)?.firstOrNull())
