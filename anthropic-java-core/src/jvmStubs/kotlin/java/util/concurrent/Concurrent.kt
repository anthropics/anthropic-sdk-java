package java.util.concurrent

open class CompletableFuture<T> {
    private var result: T? = null; private var error: Throwable? = null; private var done = false
    fun complete(value: T): Boolean { result = value; done = true; return true }
    fun completeExceptionally(ex: Throwable): Boolean { error = ex; done = true; return true }
    @Suppress("UNCHECKED_CAST") fun get(): T { error?.let { throw it }; return result as T }
    fun isDone(): Boolean = done
    @Suppress("UNCHECKED_CAST") fun <U> thenApply(fn: java.util.function.Function<in T, out U>): CompletableFuture<U> { val f = CompletableFuture<U>(); if (done) { if (error != null) f.completeExceptionally(error!!) else f.complete(fn.apply(result as T)) }; return f }
    @Suppress("UNCHECKED_CAST") fun <U> thenCompose(fn: java.util.function.Function<in T, out CompletableFuture<U>>): CompletableFuture<U> { if (done && error == null) return fn.apply(result as T); val f = CompletableFuture<U>(); if (error != null) f.completeExceptionally(error!!); return f }
    fun <U> thenComposeAsync(fn: java.util.function.Function<in T, out CompletableFuture<U>>): CompletableFuture<U> = thenCompose(fn)
    fun <U> thenComposeAsync(fn: java.util.function.Function<in T, out CompletableFuture<U>>, executor: Executor): CompletableFuture<U> = thenCompose(fn)
    fun whenComplete(action: java.util.function.BiConsumer<in T?, in Throwable?>): CompletableFuture<T> { if (done) action.accept(result, error); return this }
    fun whenCompleteAsync(action: java.util.function.BiConsumer<in T?, in Throwable?>, executor: Executor): CompletableFuture<T> = whenComplete(action)
    fun <U> handleAsync(fn: java.util.function.BiFunction<in T?, in Throwable?, out U>, executor: Executor): CompletableFuture<U> { val f = CompletableFuture<U>(); if (done) f.complete(fn.apply(result, error)); return f }
    companion object { fun <T> completedFuture(value: T): CompletableFuture<T> = CompletableFuture<T>().also { it.complete(value) } }
}
fun interface Executor { fun execute(command: java.lang.Runnable) }
interface ExecutorService : Executor {
    fun shutdown(); fun shutdownNow(): MutableList<java.lang.Runnable>; fun isShutdown(): Boolean; fun isTerminated(): Boolean
    fun awaitTermination(timeout: Long, unit: TimeUnit): Boolean
    fun <T> submit(task: Callable<T>): Future<T>; fun <T> submit(task: java.lang.Runnable, result: T): Future<T>; fun submit(task: java.lang.Runnable): Future<*>
    fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>): MutableList<Future<T>>
    fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): MutableList<Future<T>>
    fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>): T
    fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): T
}
interface Future<T> { fun get(): T; fun isDone(): Boolean; fun cancel(mayInterrupt: Boolean): Boolean; fun isCancelled(): Boolean }
fun interface Callable<T> { fun call(): T }
fun interface ThreadFactory { fun newThread(r: java.lang.Runnable): java.lang.Thread }
enum class TimeUnit { NANOSECONDS, MICROSECONDS, MILLISECONDS, SECONDS, MINUTES, HOURS, DAYS;
    fun toNanos(d: Long): Long = when (this) { NANOSECONDS -> d; MICROSECONDS -> d*1000; MILLISECONDS -> d*1000000; SECONDS -> d*1000000000; else -> d*1000000000*60 }
    fun toMillis(d: Long): Long = toNanos(d) / 1000000
}
class CompletionException : RuntimeException { constructor(message: String?, cause: Throwable?) : super(message, cause); constructor(cause: Throwable) : this(cause.message, cause) }
object Executors {
    fun newCachedThreadPool(factory: ThreadFactory? = null): ExecutorService = object : ExecutorService {
        override fun execute(command: java.lang.Runnable) { command.run() }
        override fun shutdown() {}; override fun shutdownNow(): MutableList<java.lang.Runnable> = mutableListOf()
        override fun isShutdown() = false; override fun isTerminated() = false; override fun awaitTermination(timeout: Long, unit: TimeUnit) = true
        override fun <T> submit(task: Callable<T>): Future<T> = SimpleFuture(task.call())
        override fun <T> submit(task: java.lang.Runnable, result: T): Future<T> { task.run(); return SimpleFuture(result) }
        override fun submit(task: java.lang.Runnable): Future<*> { task.run(); return SimpleFuture(Unit) }
        override fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>): MutableList<Future<T>> = tasks.map { SimpleFuture(it.call()) as Future<T> }.toMutableList()
        override fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): MutableList<Future<T>> = invokeAll(tasks)
        override fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>): T = tasks.first().call()
        override fun <T> invokeAny(tasks: MutableCollection<out Callable<T>>, timeout: Long, unit: TimeUnit): T = invokeAny(tasks)
    }
    fun defaultThreadFactory(): ThreadFactory = ThreadFactory { r -> java.lang.Thread(r) }
}
private class SimpleFuture<V>(private val value: V) : Future<V> { override fun get(): V = value; override fun isDone(): Boolean = true; override fun cancel(mayInterrupt: Boolean): Boolean = false; override fun isCancelled(): Boolean = false }
