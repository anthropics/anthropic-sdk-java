package kotlinx.kmp.util.core

import kotlin.time.Duration
import kotlinx.coroutines.delay

/**
 * Default [Sleeper] implementation backed by [kotlinx.coroutines.delay].
 *
 * In suspend contexts, prefer calling `delay()` directly. This wrapper
 * exists to satisfy the [Sleeper] interface for dependency injection
 * (e.g., fake sleepers in retry tests).
 */
class DefaultSleeper : Sleeper {

    override fun sleep(duration: Duration) = runBlockingCompat { delay(duration) }

    override suspend fun sleepSuspend(duration: Duration) = delay(duration)

    override fun close() {}
}
