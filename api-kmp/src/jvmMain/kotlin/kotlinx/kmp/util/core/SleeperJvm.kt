@file:JvmName("SleeperJvm")

package kotlinx.kmp.util.core

import java.util.concurrent.CompletableFuture
import kotlinx.coroutines.*
import kotlin.time.Duration

/**
 * JVM-only async sleep returning [CompletableFuture].
 * Common code should use [Sleeper.sleepSuspend] instead.
 */
fun Sleeper.sleepAsync(duration: Duration): CompletableFuture<Void> {
    val future = CompletableFuture<Void>()
    CoroutineScope(Dispatchers.Default).launch {
        sleepSuspend(duration)
        future.complete(null)
    }
    return future
}
