package com.anthropic.core

import com.anthropic.core.PlatformCloseable
import com.anthropic.core.PlatformDuration as Duration
import com.anthropic.core.PlatformFuture

/**
 * An interface for delaying execution for a specified amount of time.
 *
 * Useful for testing and cleaning up resources.
 */
interface Sleeper : PlatformCloseable {

    /** Synchronously pauses execution for the given [duration]. */
    fun sleep(duration: Duration)

    /** Asynchronously pauses execution for the given [duration]. */
    fun sleepAsync(duration: Duration): PlatformFuture<Void>

    /** Overridden from [PlatformCloseable] to not have a checked exception in its signature. */
    override fun close()
}
