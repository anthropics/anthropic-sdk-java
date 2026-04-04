@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package kotlinx.kmp.util.async

actual typealias CompletableFuture<T> = java.util.concurrent.CompletableFuture<T>

@Suppress("UNCHECKED_CAST")
actual fun <T> completedFuture(value: T): CompletableFuture<T> =
    java.util.concurrent.CompletableFuture.completedFuture(value)

actual fun <T> newCompletableFuture(): CompletableFuture<T> =
    java.util.concurrent.CompletableFuture<T>()

actual fun <T> failedFuture(ex: Throwable): CompletableFuture<T> {
    val f = java.util.concurrent.CompletableFuture<T>()
    f.completeExceptionally(ex)
    return f
}

actual fun <T> CompletableFuture<T>.cfComplete(value: T): Boolean = complete(value)

actual fun <T> CompletableFuture<T>.cfCompleteExceptionally(ex: Throwable): Boolean =
    completeExceptionally(ex)

actual fun <T, U> CompletableFuture<T>.cfThenCompose(
    fn: (T) -> CompletableFuture<U>
): CompletableFuture<U> = thenCompose(java.util.function.Function { fn(it) })

actual fun <T, U> CompletableFuture<T>.cfThenApply(fn: (T) -> U): CompletableFuture<U> =
    thenApply(java.util.function.Function { fn(it) })

actual fun <T> CompletableFuture<T>.cfWhenComplete(
    action: (T?, Throwable?) -> Unit
): CompletableFuture<T> = whenComplete(java.util.function.BiConsumer { t, e -> action(t, e) })

actual fun <T, U> CompletableFuture<T>.cfHandleAsync(
    fn: (T?, Throwable?) -> U,
    executor: Executor,
): CompletableFuture<U> =
    handleAsync(java.util.function.BiFunction { t, e -> fn(t, e) }, executor)

actual fun <T, U> CompletableFuture<T>.cfThenComposeAsync(
    fn: (T) -> CompletableFuture<U>,
    executor: Executor,
): CompletableFuture<U> =
    thenComposeAsync(java.util.function.Function { fn(it) }, executor)

actual fun <T> CompletableFuture<T>.cfWhenCompleteAsync(
    action: (T?, Throwable?) -> Unit,
    executor: Executor,
): CompletableFuture<T> =
    whenCompleteAsync(java.util.function.BiConsumer { t, e -> action(t, e) }, executor)

actual typealias Executor = java.util.concurrent.Executor

actual typealias AtomicReference<V> = java.util.concurrent.atomic.AtomicReference<V>

actual typealias AtomicLong = java.util.concurrent.atomic.AtomicLong
