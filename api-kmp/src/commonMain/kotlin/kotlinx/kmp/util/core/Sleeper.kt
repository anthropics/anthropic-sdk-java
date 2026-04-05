package kotlinx.kmp.util.core

import kotlin.time.Duration

interface Sleeper : AutoCloseable {
    fun sleep(duration: Duration)
    suspend fun sleepSuspend(duration: Duration) = sleep(duration)
    override fun close()
}
