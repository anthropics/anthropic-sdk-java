package com.anthropic.core

import java.time.Duration
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.CompletableFuture

class DefaultSleeper : Sleeper {

    private val timer = Timer("DefaultSleeper", true)

    // This is the `Sleeper` implementation the rule's reason text points at.
    @Suppress("ForbiddenMethodCall")
    override fun sleep(duration: Duration) = Thread.sleep(duration.toMillis())

    override fun sleepAsync(duration: Duration): CompletableFuture<Void> {
        val future = CompletableFuture<Void>()
        timer.schedule(
            object : TimerTask() {
                override fun run() {
                    future.complete(null)
                }
            },
            duration.toMillis(),
        )
        return future
    }

    override fun close() = timer.cancel()
}
