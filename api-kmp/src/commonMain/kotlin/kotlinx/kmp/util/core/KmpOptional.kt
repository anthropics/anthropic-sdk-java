@file:JvmName("KmpOptionals")
package kotlinx.kmp.util.core

import kotlinx.kmp.util.optional.orNull as _orNull
import kotlinx.kmp.util.optional.toOptional as _toOptional
import kotlinx.kmp.util.optional.getOrNull as _getOrNull

/**
 * Re-exports from kotlinx.kmp.util.optional for backward compatibility.
 *
 * The canonical definitions live in kotlinx.kmp.util.optional — a generic
 * KMP utility package independent of the Anthropic SDK. These re-exports
 * let existing code in kotlinx.kmp.util.core continue to compile without
 * changing imports.
 */

// Optional type + factory functions
typealias KmpOptional<T> = kotlinx.kmp.util.optional.Optional<T>

// Re-export factory functions
fun <T : Any> optionalOf(value: T) = kotlinx.kmp.util.optional.optionalOf(value)
fun <T : Any> optionalOfNullable(value: T?) = kotlinx.kmp.util.optional.optionalOfNullable(value)
fun <T : Any> emptyOptional() = kotlinx.kmp.util.optional.emptyOptional<T>()

// Re-export extension functions used by SDK code
fun <T : Any> KmpOptional<T>.orNull(): T? = _orNull()
fun <T : Any> T?.toOptional(): KmpOptional<T> = _toOptional()
fun <T : Any> KmpOptional<T>.getOrNull(): T? = _getOrNull()

// Re-export functional interfaces
typealias Function<T, R> = kotlinx.kmp.util.optional.Function<T, R>
typealias Supplier<T> = kotlinx.kmp.util.optional.Supplier<T>
typealias Consumer<T> = kotlinx.kmp.util.optional.Consumer<T>
typealias Predicate<T> = kotlinx.kmp.util.optional.Predicate<T>
typealias BiFunction<T, U, R> = kotlinx.kmp.util.optional.BiFunction<T, U, R>

// Re-export lambda converters
fun <T, R> toFunction(fn: (T) -> R) = kotlinx.kmp.util.optional.toFunction(fn)
fun <T> toSupplier(fn: () -> T) = kotlinx.kmp.util.optional.toSupplier(fn)
fun <T> toConsumer(fn: (T) -> Unit) = kotlinx.kmp.util.optional.toConsumer(fn)
fun <T> toPredicate(fn: (T) -> Boolean) = kotlinx.kmp.util.optional.toPredicate(fn)
fun <T, U, R> toBiFunction(fn: (T, U) -> R) = kotlinx.kmp.util.optional.toBiFunction(fn)
