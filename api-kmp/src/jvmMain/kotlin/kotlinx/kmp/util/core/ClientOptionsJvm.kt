@file:JvmName("ClientOptionsJvm")

package kotlinx.kmp.util.core

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicLong

actual fun wrapHttpClient(client: kotlinx.kmp.util.core.http.HttpClient): kotlinx.kmp.util.core.http.HttpClient =
    kotlinx.kmp.util.core.http.PhantomReachableClosingHttpClient(client)

actual fun createDefaultStreamExecutor(): Any? =
    PhantomReachableExecutorService(
        Executors.newCachedThreadPool(
            object : ThreadFactory {
                private val threadFactory: ThreadFactory = Executors.defaultThreadFactory()
                private val count = AtomicLong(0)

                override fun newThread(runnable: Runnable): Thread =
                    threadFactory.newThread(runnable).also {
                        it.name = "api-stream-handler-thread-${count.getAndIncrement()}"
                    }
            }
        )
    )

actual fun createDefaultSleeper(): Sleeper = PhantomReachableSleeper(DefaultSleeper())

actual fun shutdownStreamExecutor(executor: Any?) {
    (executor as? ExecutorService)?.shutdown()
}
