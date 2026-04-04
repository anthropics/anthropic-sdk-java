package com.anthropic.core

import java.util.concurrent.CompletableFuture
import kotlin.time.Duration
import kotlinx.coroutines.*

class DefaultSleeper : Sleeper {

    override fun sleep(duration: Duration) = Thread.sleep(duration.inWholeMilliseconds)

    override fun sleepAsync(duration: Duration): CompletableFuture<Void> {
        val future = CompletableFuture<Void>()
        CoroutineScope(Dispatchers.Default).launch {
            delay(duration)
            future.complete(null)
        }
        return future
    }

    override fun close() {}
}
