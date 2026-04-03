package com.anthropic.core

import java.util.Timer
import kotlin.time.Duration
import java.util.TimerTask
import java.util.concurrent.CompletableFuture

class DefaultSleeper : Sleeper {

    private val timer = Timer("DefaultSleeper", true)

    override fun sleep(duration: Duration) = Thread.sleep(duration.inWholeMilliseconds)

    override fun sleepAsync(duration: Duration): CompletableFuture<Void> {
        val future = CompletableFuture<Void>()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    future.complete(null)
                }
            },
            duration.inWholeMilliseconds,
        )
        return future
    }

    override fun close() = timer.cancel()
}
