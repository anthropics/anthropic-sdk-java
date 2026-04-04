@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package kotlinx.kmp.util.optional

/**
 * KMP functional interfaces — mirrors java.util.function on all targets.
 *
 * On JVM: actual typealias to java.util.function.* — SAM conversion, zero overhead.
 * On JS/Wasm/Native: pure Kotlin fun interfaces with the same contract.
 *
 * These enable Optional (and other APIs) to declare methods with functional
 * parameter types that match java.util.Optional's signatures exactly, while
 * remaining fully portable across all KMP targets.
 */

// ============================================================================
// Function<T, R> — transforms T to R
// ============================================================================

/**
 * Represents a function that accepts one argument and produces a result.
 * Mirrors java.util.function.Function<T, R>.
 */
expect fun interface Function<T, R> {
    fun apply(t: T): R
}

// ============================================================================
// Supplier<T> — produces T with no input
// ============================================================================

/**
 * Represents a supplier of results.
 * Mirrors java.util.function.Supplier<T>.
 */
expect fun interface Supplier<T> {
    fun get(): T
}

// ============================================================================
// Consumer<T> — accepts T, returns nothing
// ============================================================================

/**
 * Represents an operation that accepts a single input and returns no result.
 * Mirrors java.util.function.Consumer<T>.
 */
expect fun interface Consumer<T> {
    fun accept(t: T)
}

// ============================================================================
// Predicate<T> — tests T, returns Boolean
// ============================================================================

/**
 * Represents a predicate (boolean-valued function) of one argument.
 * Mirrors java.util.function.Predicate<T>.
 */
expect fun interface Predicate<T> {
    fun test(t: T): Boolean
}

// ============================================================================
// BiFunction<T, U, R> — transforms (T, U) to R
// ============================================================================

/**
 * Represents a function that accepts two arguments and produces a result.
 * Mirrors java.util.function.BiFunction<T, U, R>.
 */
expect fun interface BiFunction<T, U, R> {
    fun apply(t: T, u: U): R
}

// ============================================================================
// Kotlin lambda conversions — use lambdas naturally on all targets
// ============================================================================

/** Convert a Kotlin lambda to a Function. */
fun <T, R> toFunction(fn: (T) -> R): Function<T, R> = Function { t -> fn(t) }

/** Convert a Kotlin lambda to a Supplier. */
fun <T> toSupplier(fn: () -> T): Supplier<T> = Supplier { fn() }

/** Convert a Kotlin lambda to a Consumer. */
fun <T> toConsumer(fn: (T) -> Unit): Consumer<T> = Consumer { t -> fn(t) }

/** Convert a Kotlin lambda to a Predicate. */
fun <T> toPredicate(fn: (T) -> Boolean): Predicate<T> = Predicate { t -> fn(t) }

/** Convert a Kotlin lambda to a BiFunction. */
fun <T, U, R> toBiFunction(fn: (T, U) -> R): BiFunction<T, U, R> = BiFunction { t, u -> fn(t, u) }
