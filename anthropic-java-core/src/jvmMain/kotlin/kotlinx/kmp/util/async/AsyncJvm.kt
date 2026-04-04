@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
package kotlinx.kmp.util.async

/**
 * JVM actual: typealias to java.util.concurrent types.
 * Java callers get the real CompletableFuture, Executor, AtomicReference.
 */

actual typealias CompletableFuture<T> = java.util.concurrent.CompletableFuture<T>

@Suppress("UNCHECKED_CAST")
actual fun <T> completedFuture(value: T): CompletableFuture<T> =
    java.util.concurrent.CompletableFuture.completedFuture(value)

actual fun <T> newCompletableFuture(): CompletableFuture<T> =
    java.util.concurrent.CompletableFuture<T>()

@Suppress("UNCHECKED_CAST")
actual fun <T> failedFuture(ex: Throwable): CompletableFuture<T> {
    val f = java.util.concurrent.CompletableFuture<T>()
    f.completeExceptionally(ex)
    return f
}

// Extension functions delegate to real CompletableFuture methods with SAM conversion
actual fun <T> CompletableFuture<T>.cfComplete(value: T): Boolean = complete(value)
actual fun <T> CompletableFuture<T>.cfCompleteExceptionally(ex: Throwable): Boolean = completeExceptionally(ex)
actual fun <T, U> CompletableFuture<T>.cfThenCompose(fn: (T) -> CompletableFuture<U>): CompletableFuture<U> = thenCompose(fn)
actual fun <T, U> CompletableFuture<T>.cfThenApply(fn: (T) -> U): CompletableFuture<U> = thenApply(fn)
actual fun <T> CompletableFuture<T>.cfWhenComplete(action: (T?, Throwable?) -> Unit): CompletableFuture<T> = whenComplete(action)
actual fun <T, U> CompletableFuture<T>.cfHandleAsync(fn: (T?, Throwable?) -> U, executor: Executor): CompletableFuture<U> = handleAsync(fn, executor)
actual fun <T, U> CompletableFuture<T>.cfThenComposeAsync(fn: (T) -> CompletableFuture<U>, executor: Executor): CompletableFuture<U> = thenComposeAsync(fn, executor)
actual fun <T> CompletableFuture<T>.cfWhenCompleteAsync(action: (T?, Throwable?) -> Unit, executor: Executor): CompletableFuture<T> = whenCompleteAsync(action, executor)

actual typealias Executor = java.util.concurrent.Executor
actual typealias AtomicReference<V> = java.util.concurrent.atomic.AtomicReference<V>
actual typealias AtomicLong = java.util.concurrent.atomic.AtomicLong
