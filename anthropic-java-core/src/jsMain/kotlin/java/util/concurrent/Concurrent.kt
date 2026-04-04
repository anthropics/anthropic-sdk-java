// JS compile-only stubs for java.util.concurrent
package java.util.concurrent

import java.util.function.BiFunction

class CompletableFuture<T> {
    private var result: T? = null
    private var error: Throwable? = null
    private var done: Boolean = false
    private val callbacks = mutableListOf<() -> Unit>()

    fun complete(value: T): Boolean {
        if (done) return false
        result = value
        done = true
        callbacks.forEach { it() }
        return true
    }

    fun completeExceptionally(ex: Throwable): Boolean {
        if (done) return false
        error = ex
        done = true
        callbacks.forEach { it() }
        return true
    }

    @Suppress("UNCHECKED_CAST")
    fun get(): T {
        if (error != null) throw error!!
        return result as T
    }

    fun isDone(): Boolean = done

    fun <U> thenApply(fn: (T) -> U): CompletableFuture<U> {
        val next = CompletableFuture<U>()
        val action = {
            try {
                @Suppress("UNCHECKED_CAST")
                next.complete(fn(result as T))
            } catch (e: Throwable) {
                next.completeExceptionally(e)
            }
        }
        if (done) action() else callbacks.add(action)
        return next
    }

    fun <U> thenCompose(fn: (T) -> CompletableFuture<U>): CompletableFuture<U> {
        val next = CompletableFuture<U>()
        val action = {
            try {
                if (error != null) {
                    next.completeExceptionally(error!!)
                } else {
                    @Suppress("UNCHECKED_CAST")
                    val inner = fn(result as T)
                    val innerAction = {
                        if (inner.error != null) next.completeExceptionally(inner.error!!)
                        else next.complete(inner.get())
                    }
                    if (inner.done) innerAction() else inner.callbacks.add(innerAction)
                }
            } catch (e: Throwable) {
                next.completeExceptionally(e)
            }
        }
        if (done) action() else callbacks.add(action)
        return next
    }

    fun <U> thenComposeAsync(fn: (T) -> CompletableFuture<U>, executor: Executor): CompletableFuture<U> =
        thenCompose(fn)

    fun <U> handleAsync(fn: (T?, Throwable?) -> U, executor: Executor): CompletableFuture<U> {
        val next = CompletableFuture<U>()
        val action = {
            try {
                @Suppress("UNCHECKED_CAST")
                next.complete(fn(if (error == null) result as T else null, error))
            } catch (e: Throwable) {
                next.completeExceptionally(e)
            }
        }
        if (done) action() else callbacks.add(action)
        return next
    }

    fun whenComplete(action: (T?, Throwable?) -> Unit): CompletableFuture<T> {
        val cb = {
            try {
                @Suppress("UNCHECKED_CAST")
                action(if (error == null) result as T else null, error)
            } catch (_: Throwable) {}
        }
        if (done) cb() else callbacks.add(cb)
        return this
    }

    fun whenCompleteAsync(action: (T?, Throwable?) -> Unit, executor: Executor): CompletableFuture<T> {
        val cb = {
            executor.execute {
                try {
                    @Suppress("UNCHECKED_CAST")
                    action(if (error == null) result as T else null, error)
                } catch (_: Throwable) {}
            }
        }
        if (done) cb() else callbacks.add(cb)
        return this
    }

    companion object {
        fun <T> completedFuture(value: T): CompletableFuture<T> {
            val f = CompletableFuture<T>()
            f.complete(value)
            return f
        }
    }
}

class CompletionException(message: String?, cause: Throwable?) : RuntimeException(message, cause) {
    constructor(cause: Throwable?) : this(cause?.message, cause)
}

fun interface Executor {
    fun execute(command: Runnable)
}

interface ExecutorService : Executor {
    fun shutdown()
    fun shutdownNow(): MutableList<Runnable>
    fun isShutdown(): Boolean
    fun isTerminated(): Boolean
    fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean
    fun <T> submit(task: Callable<T>): Future<T>
    fun <T> submit(task: Runnable, result: T): Future<T>
    fun submit(task: Runnable): Future<*>
    fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>): MutableList<Future<T>>
    fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): MutableList<Future<T>>
    fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>): T
    fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): T
}

object Executors {
    fun newCachedThreadPool(threadFactory: ThreadFactory): ExecutorService = SimpleExecutorService()
    fun defaultThreadFactory(): ThreadFactory = ThreadFactory { Thread(it) }
}

fun interface Callable<V> {
    fun call(): V
}

interface Future<V> {
    fun get(): V
    fun isDone(): Boolean
    fun cancel(mayInterruptIfRunning: Boolean): Boolean
    fun isCancelled(): Boolean
}

fun interface ThreadFactory {
    fun newThread(r: Runnable): Thread
}

enum class TimeUnit {
    NANOSECONDS,
    MICROSECONDS,
    MILLISECONDS,
    SECONDS,
    MINUTES,
    HOURS,
    DAYS;

    fun toMillis(duration: Long): Long = when (this) {
        NANOSECONDS -> duration / 1_000_000
        MICROSECONDS -> duration / 1_000
        MILLISECONDS -> duration
        SECONDS -> duration * 1_000
        MINUTES -> duration * 60_000
        HOURS -> duration * 3_600_000
        DAYS -> duration * 86_400_000
    }
}

// Simple stub ExecutorService that runs tasks synchronously
private class SimpleExecutorService : ExecutorService {
    private var shutdown = false

    override fun execute(command: Runnable) { command.run() }
    override fun shutdown() { shutdown = true }
    override fun shutdownNow(): MutableList<Runnable> { shutdown = true; return mutableListOf() }
    override fun isShutdown(): Boolean = shutdown
    override fun isTerminated(): Boolean = shutdown
    override fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean = true
    override fun <T> submit(task: Callable<T>): Future<T> = SimpleFuture(task.call())
    override fun <T> submit(task: Runnable, result: T): Future<T> { task.run(); return SimpleFuture(result) }
    override fun submit(task: Runnable): Future<*> { task.run(); return SimpleFuture(Unit) }
    override fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>): MutableList<Future<T>> =
        tasks.map { SimpleFuture(it.call()) as Future<T> }.toMutableList()
    override fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): MutableList<Future<T>> =
        invokeAll(tasks)
    override fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>): T = tasks.first().call()
    override fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): T =
        invokeAny(tasks)
}

private class SimpleFuture<V>(private val value: V) : Future<V> {
    override fun get(): V = value
    override fun isDone(): Boolean = true
    override fun cancel(mayInterruptIfRunning: Boolean): Boolean = false
    override fun isCancelled(): Boolean = false
}
