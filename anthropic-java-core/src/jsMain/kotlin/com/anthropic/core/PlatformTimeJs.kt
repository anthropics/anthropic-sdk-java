package kotlinx.kmp.util.core

internal actual fun currentTimeNanos(): Long =
    (kotlin.js.Date.now() * 1_000_000).toLong()

internal actual fun parseRetryAfterToDelayNanos(headerValue: String, nowNanos: Long): Long? {
    headerValue.trim().toLongOrNull()?.let { seconds ->
        return seconds * 1_000_000_000
    }
    return null
}
