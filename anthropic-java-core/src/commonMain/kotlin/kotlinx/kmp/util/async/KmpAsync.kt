@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package kotlinx.kmp.util.async
import kotlinx.kmp.util.async.*

import kotlinx.kmp.util.optional.Function

/**
 * KMP async primitives — typealias to java.util.concurrent on JVM.
 *
 * Same pattern as Optional: expect class in commonMain, actual typealias on JVM.
 * Existing code keeps working unchanged. Non-JVM targets get pure Kotlin impls.
 */

// --- CompletableFuture ---
expect class CompletableFuture<T> {
    fun get(): T
    fun isDone(): Boolean
}

expect fun <T> completedFuture(value: T): CompletableFuture<T>
expect fun <T> failedFuture(ex: Throwable): CompletableFuture<T>
expect fun <T> newCompletableFuture(): CompletableFuture<T>

// Extension functions for CF methods using Java SAM types
expect fun <T> CompletableFuture<T>.cfComplete(value: T): Boolean
expect fun <T> CompletableFuture<T>.cfCompleteExceptionally(ex: Throwable): Boolean
expect fun <T, U> CompletableFuture<T>.cfThenCompose(fn: (T) -> CompletableFuture<U>): CompletableFuture<U>
expect fun <T, U> CompletableFuture<T>.cfThenApply(fn: (T) -> U): CompletableFuture<U>
expect fun <T> CompletableFuture<T>.cfWhenComplete(action: (T?, Throwable?) -> Unit): CompletableFuture<T>
expect fun <T, U> CompletableFuture<T>.cfHandleAsync(fn: (T?, Throwable?) -> U, executor: Executor): CompletableFuture<U>
expect fun <T, U> CompletableFuture<T>.cfThenComposeAsync(fn: (T) -> CompletableFuture<U>, executor: Executor): CompletableFuture<U>
expect fun <T> CompletableFuture<T>.cfWhenCompleteAsync(action: (T?, Throwable?) -> Unit, executor: Executor): CompletableFuture<T>

// --- Executor ---
expect fun interface Executor {
    fun execute(command: Runnable)
}

// --- AtomicReference ---
expect class AtomicReference<V>(initialValue: V) {
    fun get(): V
    fun set(newValue: V)
    fun getAndSet(newValue: V): V
    fun compareAndSet(expect: V, update: V): Boolean
}

// --- AtomicLong ---
expect class AtomicLong(initialValue: Long) {
    fun get(): Long
    fun getAndIncrement(): Long
}
