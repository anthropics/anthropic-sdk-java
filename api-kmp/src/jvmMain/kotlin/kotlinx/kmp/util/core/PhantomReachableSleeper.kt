package kotlinx.kmp.util.core

import kotlin.time.Duration

/**
 * A delegating wrapper around a [Sleeper] that closes it once it's only phantom reachable.
 *
 * This class ensures the [Sleeper] is closed even if the user forgets to do it.
 */
class PhantomReachableSleeper(private val sleeper: Sleeper) : Sleeper {

    init {
        closeWhenPhantomReachable(this, sleeper)
    }

    override fun sleep(duration: Duration) = sleeper.sleep(duration)

    override suspend fun sleepSuspend(duration: Duration) = sleeper.sleepSuspend(duration)

    override fun close() = sleeper.close()
}
