package kotlinx.kmp.util.core

import kotlin.time.Duration
import kotlinx.coroutines.delay

class DefaultSleeper : Sleeper {

    override fun sleep(duration: Duration) = runBlockingCompat { delay(duration) }

    override suspend fun sleepSuspend(duration: Duration) = delay(duration)

    override fun close() {}
}
