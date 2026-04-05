package kotlinx.kmp.util.core

import java.util.concurrent.CompletableFuture
import kotlin.time.Duration

interface Sleeper : java.lang.AutoCloseable {
    fun sleep(duration: Duration)
    fun sleepAsync(duration: Duration): java.util.concurrent.CompletableFuture<Void>
    override fun close()
}
