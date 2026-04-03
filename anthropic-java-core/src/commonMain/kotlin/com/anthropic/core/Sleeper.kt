package com.anthropic.core

import java.lang.AutoCloseable
import java.time.Duration
import java.util.concurrent.CompletableFuture

/**
 * An interface for delaying execution for a specified amount of time.
 *
 * Useful for testing and cleaning up resources.
 */
interface Sleeper : java.lang.AutoCloseable {

    /** Synchronously pauses execution for the given [duration]. */
    fun sleep(duration: Duration)

    /** Asynchronously pauses execution for the given [duration]. */
    fun sleepAsync(duration: Duration): java.util.concurrent.CompletableFuture<Void>

    /** Overridden from [java.lang.AutoCloseable] to not have a checked exception in its signature. */
    override fun close()
}
