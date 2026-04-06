@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package kotlinx.kmp.util.async

/** JS CompletableFuture — eager single-threaded promise wrapper. */
actual class CompletableFuture<T> {
    private var result: @UnsafeVariance T? = null
    private var error: Throwable? = null
    private var done = false
    private val handlers = mutableListOf<(T?, Throwable?) -> Unit>()

    actual fun get(): T {
        if (error != null) throw error!!
        @Suppress("UNCHECKED_CAST") return result as T
    }
    actual fun isDone(): Boolean = done

    fun complete(value: T) { result = value; done = true; fire() }
    fun completeExceptionally(ex: Throwable) { error = ex; done = true; fire() }
    internal fun onComplete(handler: (T?, Throwable?) -> Unit) { if (done) handler(result, error) else handlers.add(handler) }
    private fun fire() { handlers.forEach { it(result, error) }; handlers.clear() }
}

actual fun <T> completedFuture(value: T) = CompletableFuture<T>().also { it.complete(value) }
actual fun <T> newCompletableFuture() = CompletableFuture<T>()
actual fun <T> failedFuture(ex: Throwable) = CompletableFuture<T>().also { it.completeExceptionally(ex) }

actual fun <T> CompletableFuture<T>.cfComplete(value: T): Boolean { complete(value); return true }
actual fun <T> CompletableFuture<T>.cfCompleteExceptionally(ex: Throwable): Boolean { completeExceptionally(ex); return true }

actual fun <T, U> CompletableFuture<T>.cfThenCompose(fn: (T) -> CompletableFuture<U>): CompletableFuture<U> {
    val out = CompletableFuture<U>()
    onComplete { v, e ->
        if (e != null) out.completeExceptionally(e)
        else try { fn(v!!).onComplete { v2, e2 -> if (e2 != null) out.completeExceptionally(e2) else out.complete(v2!!) } }
        catch (t: Throwable) { out.completeExceptionally(t) }
    }
    return out
}

actual fun <T, U> CompletableFuture<T>.cfThenApply(fn: (T) -> U): CompletableFuture<U> {
    val out = CompletableFuture<U>()
    onComplete { v, e ->
        if (e != null) out.completeExceptionally(e)
        else try { out.complete(fn(v!!)) } catch (t: Throwable) { out.completeExceptionally(t) }
    }
    return out
}

actual fun <T> CompletableFuture<T>.cfWhenComplete(action: (T?, Throwable?) -> Unit): CompletableFuture<T> {
    onComplete(action); return this
}

actual fun <T, U> CompletableFuture<T>.cfHandleAsync(
    fn: (T?, Throwable?) -> U, executor: Executor
): CompletableFuture<U> {
    val out = CompletableFuture<U>()
    onComplete { v, e -> executor.execute { try { out.complete(fn(v, e)) } catch (t: Throwable) { out.completeExceptionally(t) } } }
    return out
}

actual fun <T, U> CompletableFuture<T>.cfThenComposeAsync(
    fn: (T) -> CompletableFuture<U>, executor: Executor
): CompletableFuture<U> = cfThenCompose(fn)

actual fun <T> CompletableFuture<T>.cfWhenCompleteAsync(
    action: (T?, Throwable?) -> Unit, executor: Executor
): CompletableFuture<T> = cfWhenComplete(action)

actual interface Executor {
    fun execute(command: () -> Unit)
}

actual class AtomicReference<V> actual constructor(initialValue: V) {
    private var value: V = initialValue
    actual fun get(): V = value
    actual fun set(newValue: V) { value = newValue }
    actual fun getAndSet(newValue: V): V { val old = value; value = newValue; return old }
    actual fun compareAndSet(expect: V, update: V): Boolean {
        if (value == expect) { value = update; return true }; return false
    }
}

actual class AtomicLong actual constructor(initialValue: Long) {
    private var value: Long = initialValue
    actual fun get(): Long = value
    actual fun getAndIncrement(): Long { val old = value; value++; return old }
}
